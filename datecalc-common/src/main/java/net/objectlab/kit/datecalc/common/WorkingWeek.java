/*
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.objectlab.kit.datecalc.common;

import java.util.Calendar;
import java.util.Date;

/**
 * Immutable Working Week, default is Mon->Friday.
 * 
 * @author Benoit Xhenseval
 */
public class WorkingWeek {

    protected static final byte MONDAY = 1;

    protected static final byte TUESDAY = 2;

    protected static final byte WEDNESDAY = 4;

    protected static final byte THURSDAY = 8;

    protected static final byte FRIDAY = 16;

    protected static final byte SATURDAY = 32;

    protected static final byte SUNDAY = 64;

    protected static final byte DEFAULT_WORKING_DAYS = (byte) (MONDAY + TUESDAY + WEDNESDAY + THURSDAY + FRIDAY);

    protected static final byte[] WORKING_WEEK_DAYS_OFFSET = new byte[] { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
            SATURDAY };

    public static final WorkingWeek DEFAULT = new WorkingWeek();

    /**
     * Default Working Week Monday -> Friday.
     */
    public WorkingWeek() {
        this(DEFAULT_WORKING_DAYS);
    }

    private WorkingWeek(final byte workingDays) {
        this.workingDays = workingDays;
    }

    /**
     * working days: 1 Monday, 2 Tuesday, 4 Wednesday, 8 Thursday, 16 Friday, 32
     * Saturday, 64 Sunday So Monday-Friday= 1+2+4+8+16 = 31
     */
    protected byte workingDays = DEFAULT_WORKING_DAYS;

    public boolean isWorkingDayFromCalendar(final int dayOfWeek) {
        final int day = adjustDay(dayOfWeek);
        return (WORKING_WEEK_DAYS_OFFSET[day] & workingDays) != 0;
    }

    public boolean isWorkingDay(final Date date) {
        return isWorkingDay(Utils.getCal(date));
    }

    public boolean isWorkingDay(final Calendar cal) {
        return isWorkingDayFromCalendar(cal.get(Calendar.DAY_OF_WEEK));
    }

    protected int adjustDay(final int dayOfWeek) {
        return dayOfWeek - 1;
    }

    /**
     * If the value for the given day has changed, return a NEW WorkingWeek.
     * 
     * @param working
     *            true if working day
     * @param dayOfWeek
     *            e.g. Calendar.MONDAY, Calendar.TUESDAY, etc
     * @return TODO
     */
    public WorkingWeek withWorkingDayFromCalendar(final boolean working, final int dayOfWeek) {
        final int day = adjustDay(dayOfWeek);
        WorkingWeek ret = this;
        if (working && (!isWorkingDayFromCalendar(dayOfWeek))) {
            ret = new WorkingWeek((byte) (workingDays + WORKING_WEEK_DAYS_OFFSET[day]));
        } else if (!working && isWorkingDayFromCalendar(dayOfWeek)) {
            ret = new WorkingWeek((byte) (workingDays - WORKING_WEEK_DAYS_OFFSET[day]));
        }
        return ret;
    }

    public byte getWorkingDays() {
        return workingDays;
    }
}

/*
 * $Id$
 * 
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
 */package net.objectlab.kit.datecalc.jdk;

import java.util.Calendar;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;

/**
 * TODO javadoc
 * 
 * @author Marcin Jekot
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 * 
 */
public class ModifiedPreceedingHandler extends ModifiedFollowingHandler {

    @Override
    public Calendar moveCurrentDate(final DateCalculator<Calendar> calendar) {
        return move(calendar, -1);
    }

    @Override
    public String getType() {
        return HolidayHandlerType.MODIFIED_PRECEEDING;
    }

}

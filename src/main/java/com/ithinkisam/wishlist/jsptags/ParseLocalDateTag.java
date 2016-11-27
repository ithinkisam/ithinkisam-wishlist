/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * Modifications, Copyright 2005 Stephen Colebourne, 2014-2015 Sergi Baila
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ithinkisam.wishlist.jsptags;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

/**
 * <p>
 * A handler for &lt;parseLocalDate&gt; that supports rtexprvalue-based
 * attributes.
 * </p>
 * 
 * @author Jan Luehe
 * @author Jim Newsham
 * @author Sergi Baila
 */

@SuppressWarnings("serial")
public class ParseLocalDateTag extends ParseSupport {
    @Override
    protected TemporalQuery<TemporalAccessor> temporalQuery() {
        return LocalDate::from;
    }
}
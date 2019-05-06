/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.ci.tcbot.conf;

import com.google.common.base.Strings;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BuildParameter {
    /** Name. */
    private String name;

    /** Value. */
    private String value;

    /** Random values. Ignored if exact values were specified */
    private List<String> randomValues = new LinkedList<>();

    /** {@inheritDoc} */
    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BuildParameter param = (BuildParameter)o;
        return Objects.equals(name, param.name) &&
            Objects.equals(value, param.value) &&
            Objects.equals(randomValues, param.randomValues);
    }

    /** {@inheritDoc} */
    @Override public int hashCode() {
        return Objects.hash(name, value, randomValues);
    }

    public String name() {
        return name;
    }

    public Object generateValue() {
        if (!Strings.isNullOrEmpty(value))
            return value;

        if (randomValues.isEmpty())
            return null;

        int idx = (int)(Math.random() * randomValues.size());

        return randomValues.get(idx);
    }
}

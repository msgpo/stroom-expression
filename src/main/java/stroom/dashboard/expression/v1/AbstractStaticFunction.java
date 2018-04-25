/*
 * Copyright 2017 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stroom.dashboard.expression.v1;

import java.text.ParseException;

public abstract class AbstractStaticFunction implements Function, Appendable {
    private final String name;
    private final Generator gen;

    public AbstractStaticFunction(final String name, final Var value) {
        this.name = name;
        this.gen = new Gen(value);
    }

    @Override
    public void setParams(final Param[] params) throws ParseException {
        // Ignore
    }

    @Override
    public Generator createGenerator() {
        return gen;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        appendString(sb);
        return sb.toString();
    }

    @Override
    public void appendString(final StringBuilder sb) {
        sb.append(name);
        sb.append("()");
    }

    @Override
    public boolean isAggregate() {
        return false;
    }

    @Override
    public boolean hasAggregate() {
        return false;
    }

    private static class Gen extends AbstractNoChildGenerator {
        private static final long serialVersionUID = -7551073465232523106L;

        private final Var value;

        public Gen(final Var value) {
            this.value = value;
        }

        @Override
        public Var eval() {
            return value;
        }
    }

}
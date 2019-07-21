/*
 * Copyright 2019 Immutables Authors and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.immutables.criteria.matcher;

import java.util.Objects;

/**
 * Allows to access context information of a criterion or Matcher. This interface
 * is used on <i>private</i> (generated) implementation classes when client is not supposed
 * see additional methods.
 *
 * @see org.immutables.criteria.Criterion
 */
public interface HasContext {

  CriteriaContext context();

  /**
   * Template implementation of {@link HasContext}.
   * Used as base class to instantiate different implementations of matchers.
   */
  abstract class Holder implements HasContext {
    private final CriteriaContext context;

    protected Holder(CriteriaContext context) {
      this.context = Objects.requireNonNull(context, "context");
    }

    @Override
    public CriteriaContext context() {
      return context;
    }
  }

}

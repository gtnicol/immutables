package org.immutables.modeling;

import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * 
 */
public abstract class AbstractTemplate extends Operators {

  protected final ProcessingEnvironment processing() {
    return StaticEnvironment.processing();
  }

  protected final RoundEnvironment round() {
    return StaticEnvironment.round();
  }

  protected final Set<TypeElement> annotations() {
    return StaticEnvironment.annotations();
  }
}

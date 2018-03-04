/*
 * Copyright 2018 George Aristy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.llorllale.mvn.plgn.loggit;

// @checkstyle AvoidStaticImport (1 line)
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Paths;
import org.eclipse.jgit.lib.Constants;
import org.junit.Test;

/**
 * Unit tests for {@link DefaultGit}.
 *
 * @author George Aristy (george.aristy@gmail.com)
 * @since 0.1.0
 */
public final class DefaultGitTest {

  /**
   * Log returns a reference.
   * 
   * @throws IOException unexpected
   * @since 0.1.0
   */
  @Test
  public void logIsNotNull() throws IOException {
    assertNotNull(
      new DefaultGit(Paths.get("."), Constants.MASTER).log()
    );
  }
}

/*
 * SonarQube Web Plugin
 * Copyright (C) 2010 SonarSource and Matthijs Galesloot
 * dev@sonar.codehaus.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sonar.plugins.web.checks.rgaa3;

import org.sonar.plugins.web.checks.sonar.MetaRefreshCheck;

import org.junit.Rule;
import org.junit.Test;
import org.sonar.plugins.web.checks.CheckMessagesVerifierRule;
import org.sonar.plugins.web.checks.TestHelper;
import org.sonar.plugins.web.visitor.WebSourceCode;

import java.io.File;

public class Rgaa313012CheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void detected() throws Exception {
    WebSourceCode sourceCode = TestHelper.scan(new File("src/test/resources/checks/rgaa3/Rgaa313012Check.html"), new Rgaa313012Check());

    checkMessagesVerifier.verify(sourceCode.getViolations())
        .next().atLine(2).withMessage("13.1.2 : Pour chaque page Web, chaque procédé de redirection effectué via une balise meta est-il immédiat (hors cas particuliers) ?");
  }

}

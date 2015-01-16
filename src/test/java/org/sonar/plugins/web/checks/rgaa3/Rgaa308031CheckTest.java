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

import org.junit.Ignore;

import org.junit.Rule;
import org.junit.Test;
import org.sonar.plugins.web.checks.CheckMessagesVerifierRule;
import org.sonar.plugins.web.checks.TestHelper;
import org.sonar.plugins.web.visitor.WebSourceCode;

import java.io.File;

public class Rgaa308031CheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void doctype_before_html() throws Exception {
    WebSourceCode sourceCode = TestHelper
      .scan(new File(
        "src/test/resources/checks/DoctypePresenceCheck/DoctypeBeforeHtml.html"),
        new Rgaa308031Check());

    checkMessagesVerifier.verify(sourceCode.getViolations());
  }

  @Test
  public void full_doctype_before_html() throws Exception {
    WebSourceCode sourceCode = TestHelper
      .scan(new File(
        "src/test/resources/checks/DoctypePresenceCheck/FullDoctypeBeforeHtml.html"),
        new Rgaa308031Check());

    checkMessagesVerifier.verify(sourceCode.getViolations());
  }

  @Test
  public void no_doctype_before_foo() throws Exception {
    WebSourceCode sourceCode = TestHelper
      .scan(new File(
        "src/test/resources/checks/DoctypePresenceCheck/NoDoctypeBeforeFoo.html"),
        new Rgaa308031Check());

    checkMessagesVerifier.verify(sourceCode.getViolations());
  }

  @Test
  public void no_doctype_before_html() throws Exception {
    WebSourceCode sourceCode = TestHelper
      .scan(new File(
        "src/test/resources/checks/DoctypePresenceCheck/NoDoctypeBeforeHtml.html"),
        new Rgaa308031Check());

    checkMessagesVerifier.verify(sourceCode.getViolations());
  }

  @Ignore
  public void multiple_html_tags() throws Exception {
    WebSourceCode sourceCode = TestHelper
      .scan(new File(
        "src/test/resources/checks/DoctypePresenceCheck/MultipleHtmlTags.html"),
        new Rgaa308031Check());

    checkMessagesVerifier.verify(sourceCode.getViolations()).next()
      .atLine(1);
  }

  @Test
  public void doctype_after_html() throws Exception {
    WebSourceCode sourceCode = TestHelper
      .scan(new File(
        "src/test/resources/checks/DoctypePresenceCheck/DoctypeAfterHtml.html"),
        new Rgaa308031Check());

    checkMessagesVerifier.verify(sourceCode.getViolations()).next()
      .atLine(1);
  }

  @Test
  public void no_doctype_after_html() throws Exception {
    WebSourceCode sourceCode = TestHelper
      .scan(new File(
        "src/test/resources/checks/DoctypePresenceCheck/DoctypeAfterHtml.html"),
        new Rgaa308031Check());

    checkMessagesVerifier.verify(sourceCode.getViolations()).next()
      .atLine(1);
  }

}
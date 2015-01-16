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

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.web.checks.AbstractPageCheck;
import org.sonar.plugins.web.checks.RuleTags;
import org.sonar.plugins.web.checks.WebRule;
import org.sonar.plugins.web.node.TagNode;

@Rule(
  key = "Rgaa313012Check",
  priority = Priority.MAJOR)
@WebRule(activeByDefault = true)
@RuleTags({
  RuleTags.USER_EXPERIENCE
})
public class Rgaa313012Check extends AbstractPageCheck {

  private static final String SEPERATOR = ";";

  @Override
  public void startElement(TagNode node) {
    if (isMetaRefreshTag(node)) {
      createViolation(node.getStartLinePosition(), "13.1.2 : Pour chaque page Web, chaque procédé de redirection effectué via une balise meta est-il immédiat (hors cas particuliers) ?");
    }
  }

  private static boolean isMetaRefreshTag(TagNode node) {
    String httpEquiv = node.getAttribute("HTTP-EQUIV");
    String content = node.getAttribute("content");

    return "META".equalsIgnoreCase(node.getNodeName()) && httpEquiv != null && "REFRESH".equalsIgnoreCase(httpEquiv) && isTall(content);

  }

  private static boolean isTall(String content) {

    if (content != null) {
      String[] tabContent = content.split(SEPERATOR);

      if (tabContent.length == 1) {
        return false;
      } else if (tabContent.length > 1 && !tabContent[0].equals("0")) {
        return true;
      }

      return false;
    }
    return false;
  }

}

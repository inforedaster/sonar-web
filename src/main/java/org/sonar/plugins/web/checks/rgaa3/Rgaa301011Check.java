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

@Rule(key = "Rgaa301011Check", priority = Priority.MAJOR)
@WebRule(activeByDefault = true)
@RuleTags({ RuleTags.ACCESSIBILITY })
public class Rgaa301011Check extends AbstractPageCheck {

	@Override
	public void startElement(TagNode node) {
		if (isImgTag(node)  && !hasAltAttribute(node)) {
			createViolation(node.getStartLinePosition(),
					"1.1.1 Chaque image (balise img) a-t-elle un attribut alt");
		}
	}

	private static boolean isImgTag(TagNode node) {
		return "IMG".equalsIgnoreCase(node.getNodeName());
	}

	private static boolean hasAltAttribute(TagNode node) {
		return node.getAttribute("ALT") != null;
	}

}

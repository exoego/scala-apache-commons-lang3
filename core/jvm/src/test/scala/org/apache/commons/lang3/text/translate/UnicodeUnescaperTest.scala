/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.lang3.text.translate

import org.junit.Assert._
import org.junit.Test
import org.scalatestplus.junit.JUnitSuite

/**
  * Unit tests for {@link org.apache.commons.lang3.text.translate.UnicodeEscaper}.
  */
@deprecated
class UnicodeUnescaperTest extends JUnitSuite {
  // Requested in LANG-507
  @Test def testUPlus(): Unit = {
    val uu = new UnicodeUnescaper
    val input = "\\u+0047"
    assertEquals("Failed to unescape Unicode characters with 'u+' notation", "G", uu.translate(input))
  }

  @Test def testUuuuu(): Unit = {
    val uu = new UnicodeUnescaper
    val input = "\\uuuuuuuu0047"
    val result = uu.translate(input)
    assertEquals("Failed to unescape Unicode characters with many 'u' characters", "G", result)
  }

  @Test def testLessThanFour(): Unit = {
    val uu = new UnicodeUnescaper
    val input = "\\0047\\u006"
    assertThrows[IllegalArgumentException](
      uu.translate(input)
    ) //, "A lack of digits in a Unicode escape sequence failed to throw an exception")
    ()
  }
}
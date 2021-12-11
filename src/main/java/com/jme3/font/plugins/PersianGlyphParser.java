/*
 * Copyright (c) 2009-2021 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jme3.font.plugins;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.font.GlyphParser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * An implementation of GlyphParser for Persian text.
 *
 * @author Ali-RS
 */
public class PersianGlyphParser implements GlyphParser {

    private final static PersianGlyphs GLYPHS = new PersianGlyphs();
    private final static Pattern UNJOINABLE_COMING_BEFORE = Pattern.compile("[۰-۹رآادذزوژء\\s\\d\\%\\!\\(\\)\\+\\-\\*\\/\\:\\=\\[\\]\\{\\}\\<\\>\\'\\\"\\,\\؛\\؟\\-\\#ء\\.]");
    private final static Pattern UNJOINABLE_COMING_AFTER = Pattern.compile("[۰-۹ء\\s\\d\\%\\!\\(\\)\\+\\-\\*\\/\\:\\=\\[\\]\\{\\}\\<\\>\\'\\\"\\،\\؛\\؟\\-\\#ء\\.]");

    public PersianGlyphParser() {
    }

    @Override
    public CharSequence parse(CharSequence str) {
        if (str.length() <= 1) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0, n = str.length(); i < n; i++) {
            char c = str.charAt(i);
            // Numbers require special treatment. Unlike the text they are read from left-to-right
            // so we need to reverse them.
            if (Character.isDigit(c)) {
                sb.insert(j, c); // append in "reverse" mode
                continue;
            } else {
                j = i + 1;  // store the last position of a non-digit
            }

            switch (c) {
                case 'آ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(0)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(0)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(0)[0]);
                    }
                    break;

                case 'ا':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(1)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(1)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(1)[0]);
                    }

                    break;

                case 'ب':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(2)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(2)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(2)[getShapeIndex(null, str.charAt(i + 1))]);
                    }
                    break;

                case 'پ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(3)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(3)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(3)[getShapeIndex(null, str.charAt(i + 1))]);
                    }
                    break;

                case 'ت':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(4)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(4)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(4)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ث':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(5)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(5)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(5)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ج':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(6)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(6)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(6)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'چ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(7)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(7)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(7)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ح':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(8)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(8)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(8)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'خ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(9)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(9)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(9)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'د':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(10)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(10)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(10)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ذ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(11)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(11)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(11)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ر':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(12)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(12)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(12)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ز':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(13)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(13)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(13)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ژ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(14)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(14)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(14)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'س':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(15)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(15)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(15)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ش':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(16)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(16)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(16)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ص':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(17)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(17)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(17)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ض':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(18)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(18)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(18)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;
                case 'ط':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(19)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(19)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(19)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;
                case 'ظ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(20)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(20)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(20)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ع':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(21)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(21)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(21)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'غ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(22)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(22)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(22)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;
                case 'ف':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(23)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(23)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(23)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ق':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(24)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(24)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(24)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ک':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(25)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(25)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(25)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'گ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(26)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(26)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(26)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ل':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(27)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(27)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(27)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'م':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(28)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(28)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(28)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ن':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(29)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(29)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(29)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'و':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(30)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(30)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(30)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ه':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(31)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(31)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(31)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ی':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(32)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(32)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(32)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                case 'ئ':
                    if (i == (str.length() - 1)) {
                        int x = getShapeIndex(str.charAt(i - 1), null);
                        sb.append(GLYPHS.get(33)[x]);
                    } else if (!(i == 0)) {
                        sb.append(GLYPHS.get(33)[getShapeIndex(str.charAt(i - 1), str.charAt(i + 1))]);
                    } else {
                        sb.append(GLYPHS.get(33)[getShapeIndex(null, str.charAt(i + 1))]);
                    }

                    break;

                default:
                    sb.append(str.charAt(i));

            }

        }

        return sb;
    }

    private static int getShapeIndex(Character previous, Character next) {
        int index = 0;

        boolean b = previous == null || UNJOINABLE_COMING_BEFORE.matcher(previous.toString()).matches();
        boolean n = next == null || UNJOINABLE_COMING_AFTER.matcher(next.toString()).matches();

        if (b && n) {
            // Not connected. (Isolate)
            index = 0;
        } else if (!b && n) {
            // Joined on the right. (Final)
            index = 1;
        } else if (b && !n) {
            // Joined on the left. (Initial)
            index = 2;
        } else if (!b && !n) {
            // Joined on both sides. (Medial)
            index = 3;
        }

        return index;
    }

    @Override
    public void write(JmeExporter jmeExporter) throws IOException { }

    @Override
    public void read(JmeImporter jmeImporter) throws IOException { }
}

/**
 * Contains the Persian glyphs and the different shapes each glyph has.
 */
class PersianGlyphs {

    /**
     * Keep track of Persian alphabets and their glyph shapes.
     */
    private final LinkedHashMap<Integer, char[]> glyphs = new LinkedHashMap<>();

    public PersianGlyphs() {

        glyphs.put(0, new char[]{(char) 1570, (char) 65154, (char) 1570, (char) 65154}); // 'آ'  'ـآ' الف
        glyphs.put(1, new char[]{(char) 1575, (char) 65166, (char) 1575, (char) 65166}); // 'ا' 'ـا'  الف
        glyphs.put(2, new char[]{(char) 1576, (char) 65168, (char) 65169, (char) 65170}); // 'ب' 'ـب' 'بـ' 'ـبـ' ب
        glyphs.put(3, new char[]{(char) 1662, (char) 64343, (char) 64344, (char) 64345}); // 'پ' 'ـپ' 'پـ' 'ـپـ' پ
        glyphs.put(4, new char[]{(char) 1578, (char) 65174, (char) 65175, (char) 65176}); // 'ت' 'ـت' 'تـ' 'ـتـ' ت
        glyphs.put(5, new char[]{(char) 1579, (char) 65178, (char) 65179, (char) 65180}); // 'ث' 'ـث' 'ثـ' 'ـثـ' ث
        glyphs.put(6, new char[]{(char) 1580, (char) 65182, (char) 65183, (char) 65184 }); // 'ج' 'ـج' 'جـ' 'ـجـ' ج
        glyphs.put(7, new char[]{(char) 1670, (char) 64379, (char) 64380, (char) 64381}); // 'چ' 'ـچ' 'چـ' 'ـچـ' چ
        glyphs.put(8, new char[]{(char) 1581, (char) 65186, (char) 65187, (char) 65188}); // 'ح' 'ـح' 'حـ' 'ـحـ' ح
        glyphs.put(9, new char[]{(char) 1582, (char) 65190, (char) 65191, (char) 65192}); // 'خ' 'ـخ' 'خـ' 'ـخـ' خ
        glyphs.put(10, new char[]{(char) 1583, (char) 65194, (char) 1583, (char) 65194}); // 'د' 'ـد' د
        glyphs.put(11, new char[]{(char) 1584, (char) 65196, (char) 1584, (char) 65196}); // 'ذ' 'ـذ' ذ
        glyphs.put(12, new char[]{(char) 1585, (char) 65198, (char) 1585, (char) 65198}); // 'ر' 'ـر' ر
        glyphs.put(13, new char[]{(char) 1586, (char) 65200, (char) 1586, (char) 65200}); // 'ز' 'ـز' ز
        glyphs.put(14, new char[]{(char) 1688, (char) 64395, (char) 1688, (char) 64395}); // 'ژ' 'ـژ' ژ
        glyphs.put(15, new char[]{(char) 1587, (char) 65202, (char) 65203, (char) 65204}); // 'س' 'ـس' 'سـ' 'ـسـ' س
        glyphs.put(16, new char[]{(char) 1588, (char) 65206, (char) 65207, (char) 65208}); // 'ش' 'ـش' 'شـ' 'ـشـ'ش
        glyphs.put(17, new char[]{(char) 1589, (char) 65210, (char) 65211, (char) 65212}); // 'ص' 'ـص' 'صـ' 'ـصـ'ص
        glyphs.put(18, new char[]{(char) 1590, (char) 65214, (char) 65215, (char) 65216}); // 'ض' 'ـض' 'ضـ' 'ـضـ' ض
        glyphs.put(19, new char[]{(char) 1591, (char) 65218, (char) 65219, (char) 65220}); // 'ط' 'ـط' 'طـ' 'ـطـ'ط
        glyphs.put(20, new char[]{(char) 1592, (char) 65222, (char) 65223, (char) 65224}); // 'ظ' 'ـظ' 'ظـ' 'ـظـ'ظ
        glyphs.put(21, new char[]{(char) 1593, (char) 65226, (char) 65227, (char) 65228}); // 'ع' 'ـع' 'عـ' 'ـعـ' ع
        glyphs.put(22, new char[]{(char) 1594, (char) 65230, (char) 65231, (char) 65232}); // 'غ' 'ـغ' 'غـ' 'ـغـ'غ
        glyphs.put(23, new char[]{(char) 1601, (char) 65234, (char) 65235, (char) 65236}); // 'ف' 'ـف' 'فـ' 'ـفـ'ف
        glyphs.put(24, new char[]{(char) 1602, (char) 65238, (char) 65239, (char) 65240}); // 'ق' 'ـق' 'قـ' 'ـقـ'ق
        glyphs.put(25, new char[]{(char) 1603, (char) 64399, (char) 64400, (char) 64401}); // 'ک' 'ـک' 'کـ' 'ـکـ' ک
        glyphs.put(26, new char[]{(char) 1711, (char) 64403, (char) 64404, (char) 64405}); // 'گ' 'ـگ' 'گـ' 'ـگـ'  گ
        glyphs.put(27, new char[]{(char) 1604, (char) 65246, (char) 65247, (char) 65248}); // 'ل' 'ـل' 'لـ' 'ـلـ' ل
        glyphs.put(28, new char[]{(char) 1605, (char) 65250, (char) 65251, (char) 65252}); // 'م' 'ـم' 'مـ' 'ـمـ' م
        glyphs.put(29, new char[]{(char) 1606, (char) 65254, (char) 65255, (char) 65256}); // 'ن' 'ـن' 'نـ' 'ـنـ'  ن
        glyphs.put(30, new char[]{(char) 1608, (char) 65262, (char) 1608, (char) 65262}); // 'و' 'ـو' واو
        glyphs.put(31, new char[]{(char) 1607, (char) 65258, (char) 65259, (char) 65260}); // 'ه' 'ـه' 'هـ' 'ـهـ' ه
        glyphs.put(32, new char[]{(char) 1609, (char) 64509, (char) 64510, (char) 64511}); // 'ی' 'ـی' 'یـ' 'ـیـ'  یا
        glyphs.put(33, new char[]{(char) 1574, (char) 65162, (char) 65163, (char) 65164}); // 'ئ' 'ﺊ' 'ﺋ' 'ﺌ'  ئ
    }
    
    char[] get(int key) {
        return glyphs.get(key);
    }
}

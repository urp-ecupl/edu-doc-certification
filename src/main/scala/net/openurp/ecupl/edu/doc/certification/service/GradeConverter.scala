/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.openurp.ecupl.edu.doc.certification.service

import java.text.SimpleDateFormat

import org.beangle.commons.lang.Strings
import org.openurp.edu.base.model.Student

object GradeConverter {

  def toGrade(gradeStr: String): Int = {
    val format = new SimpleDateFormat("yyyyMM")
    val year = Integer.parseInt(Strings.substringBefore(gradeStr, "-"))
    val month = Integer.parseInt(Strings.substringAfter(gradeStr, "-"))
    val date = format.format(new java.util.Date)
    val y = Integer.parseInt(date.substring(0, 4))
    val m = Integer.parseInt(date.substring(4))
    var grade = 1
    if (year == y) {
      grade = 1
    } else if (y > year) {
      if (m >= month) {
        grade = y - year + 1
      } else {
        grade = y - year
      }
    }
    grade
  }

  def getGrade(std: Student): Int = {
    toGrade(std.state.get.grade)
  }
}

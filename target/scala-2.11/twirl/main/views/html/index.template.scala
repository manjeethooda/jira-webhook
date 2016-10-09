
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""

    """),_display_(/*5.6*/play20/*5.12*/.welcome(message, style = "Java")),format.raw/*5.45*/("""

""")))}),format.raw/*7.2*/("""
"""))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Oct 09 08:33:49 IST 2016
                  SOURCE: /home/manjeet/ashu/final/firstTry/apiserver/app/views/index.scala.html
                  HASH: d81edcc7c92d43075c9cec646b97b20cc103c477
                  MATRIX: 505->1|610->18|638->21|669->44|708->46|740->53|754->59|807->92|839->95
                  LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|26->5|28->7
                  -- GENERATED --
              */
          
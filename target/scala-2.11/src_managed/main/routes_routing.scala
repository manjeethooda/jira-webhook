// @SOURCE:/home/manjeet/ashu/final/firstTry/apiserver/conf/routes
// @HASH:40ce6747d1cacd0fedb2c780581ed949372c1af7
// @DATE:Fri Oct 07 14:40:43 IST 2016


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:9
private[this] lazy val controllers_Assets_at1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at1_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:16
private[this] lazy val controllers_Users_getUserById2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/users/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Users_getUserById2_invoker = createInvoker(
controllers.Users.getUserById(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "getUserById", Seq(classOf[String]),"GET", """ get a User""", Routes.prefix + """v1/users/$id<[^/]+>"""))
        

// @LINE:17
private[this] lazy val controllers_Users_getUserByEmail3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/users/email/"),DynamicPart("email", """[^/]+""",true))))
private[this] lazy val controllers_Users_getUserByEmail3_invoker = createInvoker(
controllers.Users.getUserByEmail(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "getUserByEmail", Seq(classOf[String]),"GET", """""", Routes.prefix + """v1/users/email/$email<[^/]+>"""))
        

// @LINE:21
private[this] lazy val controllers_Users_deleteUserById4_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/users/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Users_deleteUserById4_invoker = createInvoker(
controllers.Users.deleteUserById(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "deleteUserById", Seq(classOf[String]),"DELETE", """ put a User
PUT    /v1/users/:id 					controllers.Users.putUserById(id: String)
 delete a User""", Routes.prefix + """v1/users/$id<[^/]+>"""))
        

// @LINE:24
private[this] lazy val controllers_Users_getUsers5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/users"))))
private[this] lazy val controllers_Users_getUsers5_invoker = createInvoker(
controllers.Users.getUsers(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "getUsers", Nil,"GET", """ get from Users collection""", Routes.prefix + """v1/users"""))
        

// @LINE:26
private[this] lazy val controllers_Users_postUsers6_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/users"))))
private[this] lazy val controllers_Users_postUsers6_invoker = createInvoker(
controllers.Users.postUsers(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "postUsers", Nil,"POST", """ post into Users collection""", Routes.prefix + """v1/users"""))
        

// @LINE:28
private[this] lazy val controllers_Users_putUsers7_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/users"))))
private[this] lazy val controllers_Users_putUsers7_invoker = createInvoker(
controllers.Users.putUsers(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "putUsers", Nil,"PUT", """ put into Users collection""", Routes.prefix + """v1/users"""))
        

// @LINE:38
private[this] lazy val controllers_Expenses_getExpenseById8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/expenses/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Expenses_getExpenseById8_invoker = createInvoker(
controllers.Expenses.getExpenseById(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Expenses", "getExpenseById", Seq(classOf[String]),"GET", """ get an Expense""", Routes.prefix + """v1/expenses/$id<[^/]+>"""))
        

// @LINE:45
private[this] lazy val controllers_Expenses_getExpenses9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/expenses"))))
private[this] lazy val controllers_Expenses_getExpenses9_invoker = createInvoker(
controllers.Expenses.getExpenses(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Expenses", "getExpenses", Nil,"GET", """ get from Expenses collection""", Routes.prefix + """v1/expenses"""))
        

// @LINE:47
private[this] lazy val controllers_Expenses_postExpenses10_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/expenses"))))
private[this] lazy val controllers_Expenses_postExpenses10_invoker = createInvoker(
controllers.Expenses.postExpenses(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Expenses", "postExpenses", Nil,"POST", """ post into Users collection""", Routes.prefix + """v1/expenses"""))
        

// @LINE:59
private[this] lazy val controllers_KosyncIssue_getIssueById11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/issue"))))
private[this] lazy val controllers_KosyncIssue_getIssueById11_invoker = createInvoker(
controllers.KosyncIssue.getIssueById(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssue", "getIssueById", Nil,"GET", """get an Issue""", Routes.prefix + """v1/issue"""))
        

// @LINE:67
private[this] lazy val controllers_KosyncProject_getProject12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/projects/"),DynamicPart("key", """[^/]+""",true))))
private[this] lazy val controllers_KosyncProject_getProject12_invoker = createInvoker(
controllers.KosyncProject.getProject(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProject", "getProject", Seq(classOf[String]),"GET", """GET a project""", Routes.prefix + """v1/projects/$key<[^/]+>"""))
        

// @LINE:70
private[this] lazy val controllers_KosyncProject_putProject13_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/projects/"),DynamicPart("key", """[^/]+""",true))))
private[this] lazy val controllers_KosyncProject_putProject13_invoker = createInvoker(
controllers.KosyncProject.putProject(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProject", "putProject", Seq(classOf[String]),"PUT", """put a Project""", Routes.prefix + """v1/projects/$key<[^/]+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users/$id<[^/]+>""","""controllers.Users.getUserById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users/email/$email<[^/]+>""","""controllers.Users.getUserByEmail(email:String)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users/$id<[^/]+>""","""controllers.Users.deleteUserById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users""","""controllers.Users.getUsers()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users""","""controllers.Users.postUsers()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users""","""controllers.Users.putUsers()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/expenses/$id<[^/]+>""","""controllers.Expenses.getExpenseById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/expenses""","""controllers.Expenses.getExpenses()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/expenses""","""controllers.Expenses.postExpenses()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/issue""","""controllers.KosyncIssue.getIssueById()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/projects/$key<[^/]+>""","""controllers.KosyncProject.getProject(key:String)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/projects/$key<[^/]+>""","""controllers.KosyncProject.putProject(key:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:9
case controllers_Assets_at1_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at1_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:16
case controllers_Users_getUserById2_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_Users_getUserById2_invoker.call(controllers.Users.getUserById(id))
   }
}
        

// @LINE:17
case controllers_Users_getUserByEmail3_route(params) => {
   call(params.fromPath[String]("email", None)) { (email) =>
        controllers_Users_getUserByEmail3_invoker.call(controllers.Users.getUserByEmail(email))
   }
}
        

// @LINE:21
case controllers_Users_deleteUserById4_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_Users_deleteUserById4_invoker.call(controllers.Users.deleteUserById(id))
   }
}
        

// @LINE:24
case controllers_Users_getUsers5_route(params) => {
   call { 
        controllers_Users_getUsers5_invoker.call(controllers.Users.getUsers())
   }
}
        

// @LINE:26
case controllers_Users_postUsers6_route(params) => {
   call { 
        controllers_Users_postUsers6_invoker.call(controllers.Users.postUsers())
   }
}
        

// @LINE:28
case controllers_Users_putUsers7_route(params) => {
   call { 
        controllers_Users_putUsers7_invoker.call(controllers.Users.putUsers())
   }
}
        

// @LINE:38
case controllers_Expenses_getExpenseById8_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_Expenses_getExpenseById8_invoker.call(controllers.Expenses.getExpenseById(id))
   }
}
        

// @LINE:45
case controllers_Expenses_getExpenses9_route(params) => {
   call { 
        controllers_Expenses_getExpenses9_invoker.call(controllers.Expenses.getExpenses())
   }
}
        

// @LINE:47
case controllers_Expenses_postExpenses10_route(params) => {
   call { 
        controllers_Expenses_postExpenses10_invoker.call(controllers.Expenses.postExpenses())
   }
}
        

// @LINE:59
case controllers_KosyncIssue_getIssueById11_route(params) => {
   call { 
        controllers_KosyncIssue_getIssueById11_invoker.call(controllers.KosyncIssue.getIssueById())
   }
}
        

// @LINE:67
case controllers_KosyncProject_getProject12_route(params) => {
   call(params.fromPath[String]("key", None)) { (key) =>
        controllers_KosyncProject_getProject12_invoker.call(controllers.KosyncProject.getProject(key))
   }
}
        

// @LINE:70
case controllers_KosyncProject_putProject13_route(params) => {
   call(params.fromPath[String]("key", None)) { (key) =>
        controllers_KosyncProject_putProject13_invoker.call(controllers.KosyncProject.putProject(key))
   }
}
        
}

}
     
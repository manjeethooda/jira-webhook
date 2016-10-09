// @SOURCE:/home/manjeet/ashu/final/firstTry/apiserver/conf/routes
// @HASH:de7b0e5c36ea58dd27d09a9916032d8eb4c56411
// @DATE:Sun Oct 09 10:17:54 IST 2016


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
private[this] lazy val controllers_KosyncIssues_getIssueById11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/issues/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_KosyncIssues_getIssueById11_invoker = createInvoker(
controllers.KosyncIssues.getIssueById(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssueById", Seq(classOf[String]),"GET", """GET an Issue""", Routes.prefix + """v1/issues/$id<[^/]+>"""))
        

// @LINE:61
private[this] lazy val controllers_KosyncIssues_getIssuesByKey12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/issues/key/"),DynamicPart("key", """[^/]+""",true))))
private[this] lazy val controllers_KosyncIssues_getIssuesByKey12_invoker = createInvoker(
controllers.KosyncIssues.getIssuesByKey(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssuesByKey", Seq(classOf[String]),"GET", """""", Routes.prefix + """v1/issues/key/$key<[^/]+>"""))
        

// @LINE:63
private[this] lazy val controllers_KosyncIssues_getIssueByParent13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/issues/parent/"),DynamicPart("parent", """[^/]+""",true))))
private[this] lazy val controllers_KosyncIssues_getIssueByParent13_invoker = createInvoker(
controllers.KosyncIssues.getIssueByParent(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssueByParent", Seq(classOf[String]),"GET", """""", Routes.prefix + """v1/issues/parent/$parent<[^/]+>"""))
        

// @LINE:65
private[this] lazy val controllers_KosyncIssues_getIssueByChild14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/issues/child/"),DynamicPart("child", """[^/]+""",true))))
private[this] lazy val controllers_KosyncIssues_getIssueByChild14_invoker = createInvoker(
controllers.KosyncIssues.getIssueByChild(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssueByChild", Seq(classOf[String]),"GET", """""", Routes.prefix + """v1/issues/child/$child<[^/]+>"""))
        

// @LINE:68
private[this] lazy val controllers_KosyncIssues_postIssues15_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/issues"))))
private[this] lazy val controllers_KosyncIssues_postIssues15_invoker = createInvoker(
controllers.KosyncIssues.postIssues(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "postIssues", Nil,"POST", """post into Issues collection""", Routes.prefix + """v1/issues"""))
        

// @LINE:71
private[this] lazy val controllers_KosyncIssues_deleteIssueById16_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/issues/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_KosyncIssues_deleteIssueById16_invoker = createInvoker(
controllers.KosyncIssues.deleteIssueById(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "deleteIssueById", Seq(classOf[String]),"DELETE", """ delete a Issue""", Routes.prefix + """v1/issues/$id<[^/]+>"""))
        

// @LINE:79
private[this] lazy val controllers_KosyncProjects_getProjectById17_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/projects/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_KosyncProjects_getProjectById17_invoker = createInvoker(
controllers.KosyncProjects.getProjectById(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProjectById", Seq(classOf[String]),"GET", """GET a project""", Routes.prefix + """v1/projects/$id<[^/]+>"""))
        

// @LINE:81
private[this] lazy val controllers_KosyncProjects_getProjectsByKey18_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/projects/key/"),DynamicPart("key", """[^/]+""",true))))
private[this] lazy val controllers_KosyncProjects_getProjectsByKey18_invoker = createInvoker(
controllers.KosyncProjects.getProjectsByKey(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProjectsByKey", Seq(classOf[String]),"GET", """""", Routes.prefix + """v1/projects/key/$key<[^/]+>"""))
        

// @LINE:83
private[this] lazy val controllers_KosyncProjects_getProjectByURL19_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/projects/URL/"),DynamicPart("URL", """[^/]+""",true))))
private[this] lazy val controllers_KosyncProjects_getProjectByURL19_invoker = createInvoker(
controllers.KosyncProjects.getProjectByURL(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProjectByURL", Seq(classOf[String]),"GET", """""", Routes.prefix + """v1/projects/URL/$URL<[^/]+>"""))
        

// @LINE:86
private[this] lazy val controllers_KosyncProjects_postProjects20_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/projects"))))
private[this] lazy val controllers_KosyncProjects_postProjects20_invoker = createInvoker(
controllers.KosyncProjects.postProjects(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "postProjects", Nil,"POST", """post into Projects collection""", Routes.prefix + """v1/projects"""))
        

// @LINE:89
private[this] lazy val controllers_KosyncProjects_deleteProjectById21_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("v1/projects/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_KosyncProjects_deleteProjectById21_invoker = createInvoker(
controllers.KosyncProjects.deleteProjectById(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "deleteProjectById", Seq(classOf[String]),"DELETE", """ delete a Project""", Routes.prefix + """v1/projects/$id<[^/]+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users/$id<[^/]+>""","""controllers.Users.getUserById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users/email/$email<[^/]+>""","""controllers.Users.getUserByEmail(email:String)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users/$id<[^/]+>""","""controllers.Users.deleteUserById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users""","""controllers.Users.getUsers()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users""","""controllers.Users.postUsers()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/users""","""controllers.Users.putUsers()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/expenses/$id<[^/]+>""","""controllers.Expenses.getExpenseById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/expenses""","""controllers.Expenses.getExpenses()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/expenses""","""controllers.Expenses.postExpenses()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/issues/$id<[^/]+>""","""controllers.KosyncIssues.getIssueById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/issues/key/$key<[^/]+>""","""controllers.KosyncIssues.getIssuesByKey(key:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/issues/parent/$parent<[^/]+>""","""controllers.KosyncIssues.getIssueByParent(parent:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/issues/child/$child<[^/]+>""","""controllers.KosyncIssues.getIssueByChild(child:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/issues""","""controllers.KosyncIssues.postIssues()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/issues/$id<[^/]+>""","""controllers.KosyncIssues.deleteIssueById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/projects/$id<[^/]+>""","""controllers.KosyncProjects.getProjectById(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/projects/key/$key<[^/]+>""","""controllers.KosyncProjects.getProjectsByKey(key:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/projects/URL/$URL<[^/]+>""","""controllers.KosyncProjects.getProjectByURL(URL:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/projects""","""controllers.KosyncProjects.postProjects()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """v1/projects/$id<[^/]+>""","""controllers.KosyncProjects.deleteProjectById(id:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
case controllers_KosyncIssues_getIssueById11_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_KosyncIssues_getIssueById11_invoker.call(controllers.KosyncIssues.getIssueById(id))
   }
}
        

// @LINE:61
case controllers_KosyncIssues_getIssuesByKey12_route(params) => {
   call(params.fromPath[String]("key", None)) { (key) =>
        controllers_KosyncIssues_getIssuesByKey12_invoker.call(controllers.KosyncIssues.getIssuesByKey(key))
   }
}
        

// @LINE:63
case controllers_KosyncIssues_getIssueByParent13_route(params) => {
   call(params.fromPath[String]("parent", None)) { (parent) =>
        controllers_KosyncIssues_getIssueByParent13_invoker.call(controllers.KosyncIssues.getIssueByParent(parent))
   }
}
        

// @LINE:65
case controllers_KosyncIssues_getIssueByChild14_route(params) => {
   call(params.fromPath[String]("child", None)) { (child) =>
        controllers_KosyncIssues_getIssueByChild14_invoker.call(controllers.KosyncIssues.getIssueByChild(child))
   }
}
        

// @LINE:68
case controllers_KosyncIssues_postIssues15_route(params) => {
   call { 
        controllers_KosyncIssues_postIssues15_invoker.call(controllers.KosyncIssues.postIssues())
   }
}
        

// @LINE:71
case controllers_KosyncIssues_deleteIssueById16_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_KosyncIssues_deleteIssueById16_invoker.call(controllers.KosyncIssues.deleteIssueById(id))
   }
}
        

// @LINE:79
case controllers_KosyncProjects_getProjectById17_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_KosyncProjects_getProjectById17_invoker.call(controllers.KosyncProjects.getProjectById(id))
   }
}
        

// @LINE:81
case controllers_KosyncProjects_getProjectsByKey18_route(params) => {
   call(params.fromPath[String]("key", None)) { (key) =>
        controllers_KosyncProjects_getProjectsByKey18_invoker.call(controllers.KosyncProjects.getProjectsByKey(key))
   }
}
        

// @LINE:83
case controllers_KosyncProjects_getProjectByURL19_route(params) => {
   call(params.fromPath[String]("URL", None)) { (URL) =>
        controllers_KosyncProjects_getProjectByURL19_invoker.call(controllers.KosyncProjects.getProjectByURL(URL))
   }
}
        

// @LINE:86
case controllers_KosyncProjects_postProjects20_route(params) => {
   call { 
        controllers_KosyncProjects_postProjects20_invoker.call(controllers.KosyncProjects.postProjects())
   }
}
        

// @LINE:89
case controllers_KosyncProjects_deleteProjectById21_route(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        controllers_KosyncProjects_deleteProjectById21_invoker.call(controllers.KosyncProjects.deleteProjectById(id))
   }
}
        
}

}
     
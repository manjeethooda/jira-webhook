// @SOURCE:/home/manjeet/ashu/latest/jira-webhook/conf/routes
// @HASH:372ffbc33ab252833e5b228dfec7247e1f3382ee
// @DATE:Thu Oct 13 22:07:47 IST 2016

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:92
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:73
// @LINE:70
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:47
// @LINE:45
// @LINE:38
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:21
// @LINE:17
// @LINE:16
// @LINE:9
// @LINE:6
package controllers {

// @LINE:73
// @LINE:70
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
class ReverseKosyncIssues {


// @LINE:59
def getIssueById(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/issues/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

// @LINE:73
def deleteIssueById(id:String): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "v1/issues/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

// @LINE:61
def getIssues(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/issues")
}
                        

// @LINE:65
def getIssueByChild(child:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/issues/child/" + implicitly[PathBindable[String]].unbind("child", dynamicString(child)))
}
                        

// @LINE:70
def postIssues(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "v1/issues")
}
                        

// @LINE:63
def getIssueByParent(parent:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/issues/parent/" + implicitly[PathBindable[String]].unbind("parent", dynamicString(parent)))
}
                        

// @LINE:67
def getIssuesByKey(key:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/issues/key/" + implicitly[PathBindable[String]].unbind("key", dynamicString(key)))
}
                        

}
                          

// @LINE:9
class ReverseAssets {


// @LINE:9
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:92
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
class ReverseKosyncProjects {


// @LINE:89
def postProjects(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "v1/projects")
}
                        

// @LINE:92
def deleteProjectById(id:String): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "v1/projects/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

// @LINE:87
def getProjectsByKey(key:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/projects/key/" + implicitly[PathBindable[String]].unbind("key", dynamicString(key)))
}
                        

// @LINE:83
def getProjects(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/projects")
}
                        

// @LINE:85
def getProjectByURL(URL:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/projects/URL/" + implicitly[PathBindable[String]].unbind("URL", dynamicString(URL)))
}
                        

// @LINE:81
def getProjectById(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/projects/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

}
                          

// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:21
// @LINE:17
// @LINE:16
class ReverseUsers {


// @LINE:17
def getUserByEmail(email:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/users/email/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)))
}
                        

// @LINE:16
def getUserById(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/users/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

// @LINE:26
def postUsers(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "v1/users")
}
                        

// @LINE:21
def deleteUserById(id:String): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "v1/users/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

// @LINE:24
def getUsers(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/users")
}
                        

// @LINE:28
def putUsers(): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "v1/users")
}
                        

}
                          

// @LINE:47
// @LINE:45
// @LINE:38
class ReverseExpenses {


// @LINE:45
def getExpenses(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/expenses")
}
                        

// @LINE:47
def postExpenses(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "v1/expenses")
}
                        

// @LINE:38
def getExpenseById(id:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/expenses/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
}
                        

}
                          

// @LINE:6
class ReverseApplication {


// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          
}
                  


// @LINE:92
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:73
// @LINE:70
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:47
// @LINE:45
// @LINE:38
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:21
// @LINE:17
// @LINE:16
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:73
// @LINE:70
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
class ReverseKosyncIssues {


// @LINE:59
def getIssueById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssues.getIssueById",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issues/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:73
def deleteIssueById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssues.deleteIssueById",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issues/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:61
def getIssues : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssues.getIssues",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issues"})
      }
   """
)
                        

// @LINE:65
def getIssueByChild : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssues.getIssueByChild",
   """
      function(child) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issues/child/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("child", encodeURIComponent(child))})
      }
   """
)
                        

// @LINE:70
def postIssues : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssues.postIssues",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issues"})
      }
   """
)
                        

// @LINE:63
def getIssueByParent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssues.getIssueByParent",
   """
      function(parent) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issues/parent/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("parent", encodeURIComponent(parent))})
      }
   """
)
                        

// @LINE:67
def getIssuesByKey : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssues.getIssuesByKey",
   """
      function(key) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issues/key/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("key", encodeURIComponent(key))})
      }
   """
)
                        

}
              

// @LINE:9
class ReverseAssets {


// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:92
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
class ReverseKosyncProjects {


// @LINE:89
def postProjects : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.postProjects",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects"})
      }
   """
)
                        

// @LINE:92
def deleteProjectById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.deleteProjectById",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:87
def getProjectsByKey : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.getProjectsByKey",
   """
      function(key) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects/key/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("key", encodeURIComponent(key))})
      }
   """
)
                        

// @LINE:83
def getProjects : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.getProjects",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects"})
      }
   """
)
                        

// @LINE:85
def getProjectByURL : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.getProjectByURL",
   """
      function(URL) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects/URL/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("URL", encodeURIComponent(URL))})
      }
   """
)
                        

// @LINE:81
def getProjectById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.getProjectById",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

}
              

// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:21
// @LINE:17
// @LINE:16
class ReverseUsers {


// @LINE:17
def getUserByEmail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Users.getUserByEmail",
   """
      function(email) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/users/email/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email))})
      }
   """
)
                        

// @LINE:16
def getUserById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Users.getUserById",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:26
def postUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Users.postUsers",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/users"})
      }
   """
)
                        

// @LINE:21
def deleteUserById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Users.deleteUserById",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:24
def getUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Users.getUsers",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/users"})
      }
   """
)
                        

// @LINE:28
def putUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Users.putUsers",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/users"})
      }
   """
)
                        

}
              

// @LINE:47
// @LINE:45
// @LINE:38
class ReverseExpenses {


// @LINE:45
def getExpenses : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Expenses.getExpenses",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/expenses"})
      }
   """
)
                        

// @LINE:47
def postExpenses : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Expenses.postExpenses",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/expenses"})
      }
   """
)
                        

// @LINE:38
def getExpenseById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Expenses.getExpenseById",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/expenses/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

}
              

// @LINE:6
class ReverseApplication {


// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              
}
        


// @LINE:92
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:73
// @LINE:70
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:47
// @LINE:45
// @LINE:38
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:21
// @LINE:17
// @LINE:16
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:73
// @LINE:70
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
class ReverseKosyncIssues {


// @LINE:59
def getIssueById(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssues.getIssueById(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssueById", Seq(classOf[String]), "GET", """GET an Issue""", _prefix + """v1/issues/$id<[^/]+>""")
)
                      

// @LINE:73
def deleteIssueById(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssues.deleteIssueById(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "deleteIssueById", Seq(classOf[String]), "DELETE", """ delete a Issue""", _prefix + """v1/issues/$id<[^/]+>""")
)
                      

// @LINE:61
def getIssues(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssues.getIssues(), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssues", Seq(), "GET", """""", _prefix + """v1/issues""")
)
                      

// @LINE:65
def getIssueByChild(child:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssues.getIssueByChild(child), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssueByChild", Seq(classOf[String]), "GET", """""", _prefix + """v1/issues/child/$child<[^/]+>""")
)
                      

// @LINE:70
def postIssues(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssues.postIssues(), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "postIssues", Seq(), "POST", """post into Issues collection""", _prefix + """v1/issues""")
)
                      

// @LINE:63
def getIssueByParent(parent:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssues.getIssueByParent(parent), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssueByParent", Seq(classOf[String]), "GET", """""", _prefix + """v1/issues/parent/$parent<[^/]+>""")
)
                      

// @LINE:67
def getIssuesByKey(key:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssues.getIssuesByKey(key), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssues", "getIssuesByKey", Seq(classOf[String]), "GET", """""", _prefix + """v1/issues/key/$key<[^/]+>""")
)
                      

}
                          

// @LINE:9
class ReverseAssets {


// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:92
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
class ReverseKosyncProjects {


// @LINE:89
def postProjects(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.postProjects(), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "postProjects", Seq(), "POST", """post into Projects collection""", _prefix + """v1/projects""")
)
                      

// @LINE:92
def deleteProjectById(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.deleteProjectById(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "deleteProjectById", Seq(classOf[String]), "DELETE", """ delete a Project""", _prefix + """v1/projects/$id<[^/]+>""")
)
                      

// @LINE:87
def getProjectsByKey(key:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.getProjectsByKey(key), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProjectsByKey", Seq(classOf[String]), "GET", """""", _prefix + """v1/projects/key/$key<[^/]+>""")
)
                      

// @LINE:83
def getProjects(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.getProjects(), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProjects", Seq(), "GET", """""", _prefix + """v1/projects""")
)
                      

// @LINE:85
def getProjectByURL(URL:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.getProjectByURL(URL), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProjectByURL", Seq(classOf[String]), "GET", """""", _prefix + """v1/projects/URL/$URL<[^/]+>""")
)
                      

// @LINE:81
def getProjectById(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.getProjectById(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProjectById", Seq(classOf[String]), "GET", """GET a project""", _prefix + """v1/projects/$id<[^/]+>""")
)
                      

}
                          

// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:21
// @LINE:17
// @LINE:16
class ReverseUsers {


// @LINE:17
def getUserByEmail(email:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Users.getUserByEmail(email), HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "getUserByEmail", Seq(classOf[String]), "GET", """""", _prefix + """v1/users/email/$email<[^/]+>""")
)
                      

// @LINE:16
def getUserById(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Users.getUserById(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "getUserById", Seq(classOf[String]), "GET", """ get a User""", _prefix + """v1/users/$id<[^/]+>""")
)
                      

// @LINE:26
def postUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Users.postUsers(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "postUsers", Seq(), "POST", """ post into Users collection""", _prefix + """v1/users""")
)
                      

// @LINE:21
def deleteUserById(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Users.deleteUserById(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "deleteUserById", Seq(classOf[String]), "DELETE", """ put a User
PUT    /v1/users/:id 					controllers.Users.putUserById(id: String)
 delete a User""", _prefix + """v1/users/$id<[^/]+>""")
)
                      

// @LINE:24
def getUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Users.getUsers(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "getUsers", Seq(), "GET", """ get from Users collection""", _prefix + """v1/users""")
)
                      

// @LINE:28
def putUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Users.putUsers(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Users", "putUsers", Seq(), "PUT", """ put into Users collection""", _prefix + """v1/users""")
)
                      

}
                          

// @LINE:47
// @LINE:45
// @LINE:38
class ReverseExpenses {


// @LINE:45
def getExpenses(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Expenses.getExpenses(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Expenses", "getExpenses", Seq(), "GET", """ get from Expenses collection""", _prefix + """v1/expenses""")
)
                      

// @LINE:47
def postExpenses(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Expenses.postExpenses(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Expenses", "postExpenses", Seq(), "POST", """ post into Users collection""", _prefix + """v1/expenses""")
)
                      

// @LINE:38
def getExpenseById(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Expenses.getExpenseById(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Expenses", "getExpenseById", Seq(classOf[String]), "GET", """ get an Expense""", _prefix + """v1/expenses/$id<[^/]+>""")
)
                      

}
                          

// @LINE:6
class ReverseApplication {


// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          
}
        
    
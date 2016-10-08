// @SOURCE:/home/manjeet/ashu/final/firstTry/apiserver/conf/routes
// @HASH:3a19dd9608c3b9fce749bd6d1e08d9f38e539eda
// @DATE:Fri Oct 07 18:10:16 IST 2016

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:70
// @LINE:67
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

// @LINE:9
class ReverseAssets {


// @LINE:9
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:70
// @LINE:67
class ReverseKosyncProjects {


// @LINE:70
def postProjects(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "v1/projects")
}
                        

// @LINE:67
def getProject(key:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/projects/" + implicitly[PathBindable[String]].unbind("key", dynamicString(key)))
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
                          

// @LINE:59
class ReverseKosyncIssue {


// @LINE:59
def getIssueById(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "v1/issue")
}
                        

}
                          
}
                  


// @LINE:70
// @LINE:67
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
              

// @LINE:70
// @LINE:67
class ReverseKosyncProjects {


// @LINE:70
def postProjects : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.postProjects",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects"})
      }
   """
)
                        

// @LINE:67
def getProject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncProjects.getProject",
   """
      function(key) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/projects/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("key", encodeURIComponent(key))})
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
              

// @LINE:59
class ReverseKosyncIssue {


// @LINE:59
def getIssueById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.KosyncIssue.getIssueById",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/issue"})
      }
   """
)
                        

}
              
}
        


// @LINE:70
// @LINE:67
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


// @LINE:9
class ReverseAssets {


// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:70
// @LINE:67
class ReverseKosyncProjects {


// @LINE:70
def postProjects(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.postProjects(), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "postProjects", Seq(), "POST", """put a Project""", _prefix + """v1/projects""")
)
                      

// @LINE:67
def getProject(key:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncProjects.getProject(key), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncProjects", "getProject", Seq(classOf[String]), "GET", """GET a project""", _prefix + """v1/projects/$key<[^/]+>""")
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
                          

// @LINE:59
class ReverseKosyncIssue {


// @LINE:59
def getIssueById(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.KosyncIssue.getIssueById(), HandlerDef(this.getClass.getClassLoader, "", "controllers.KosyncIssue", "getIssueById", Seq(), "GET", """get an Issue""", _prefix + """v1/issue""")
)
                      

}
                          
}
        
    
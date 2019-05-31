<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="es">

<!-- partial:partials/_head.jsp -->
<%@include file="../../_partials/_head.jsp" %>
<!-- partial -->

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth lock-full-bg">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-transparent text-left p-5 text-center">
              <img src="<c:url value="/assets/images/faces/face13.jpg" />" class="lock-profile-img" alt="img">
              <form class="pt-5">
                <div class="form-group">
                  <label for="examplePassword1">Password to unlock</label>
                  <input type="password" class="form-control text-center" id="examplePassword1" placeholder="Password">
                </div>
                <div class="mt-5">
                  <a class="btn btn-block btn-success btn-lg font-weight-medium" href="/si2/">Unlock</a>
                </div>
                <div class="mt-3 text-center">
                  <a href="/si2/user-admin-samples/login" class="auth-link text-white">Sign in using a different account</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- partial:partials/_scripts.jsp -->
  <%@include file="../../_partials/_scripts.jsp" %>
  <!-- partial -->
</body>

</html>

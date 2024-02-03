<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sign In</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Add styles for the header and background color */
    body {
      background-color: #f8f8f8;
      background-image: url("undraw_wait_in_line_o2aq.png"); /* Replace 'your-background-image-url.jpg' with the actual URL of your background image */
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
      color: #333; /* Set the text color */
    }

    .header {
      background-color:#D3D3D3; /* Set the header background color */
      color: #333; /* Set the header text color */
      padding: 10px;
      text-align: left;
    }
    
    
    .card-header {
      background-color: #D3D3D3 !important; /* Set the card header background color */
      color: #333; /* Set the card header text color */
    }

    .btn-primary {
      background-color: #D3D3D3; /* Set the button background color */
      border-color: #D3D3D3; /* Set the button border color */
      color: #333; /* Set the button text color */
    }

    /* Add additional styles as needed */
  </style>
</head>

<body>
  <!-- Header -->
  <div class="header">
    <h1>JOBIFY.com</h1>
    <!-- Add any additional header content as needed -->
  </div>

  <div class="container mt-5">
    <div class="card mx-auto rounded" style="max-width: 400px;">
        <div class="card-header bg-primary text-white text-center">
            <h5 class="mb-0">Sign In</h5>
        </div>
        <div class="card-body">
            <form action="login" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                    <p class="text-danger small">${noAccountFound}</p>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                    <p class="text-danger small">${pf}</p>
                    <p class="text-danger small">${accountBlocked}</p>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Sign In</button>
            </form>
            <div class="mt-3 text-center">
              <a href="forgot.jsp">Forgot Password?</a>
          </div>
        </div>
    </div>
</div>

  <!-- Bootstrap JS and dependencies (jQuery and Popper.js) -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>

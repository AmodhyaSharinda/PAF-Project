import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
//import PrivateRoute from "./components/PrivateRoute";


import Login from "./pages/public/login";
import Home from "./pages/public/home";
import Posts from "./pages/private/posts";
import CreatePost from "./pages/private/CreatePost"; // Ensure this is the correct path
import UpdatePost from "./pages/private/UpdatePost"; // Ensure this is the correct path


function App() {
  return (
    <AuthProvider>
      <Router>
        
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/home" element={<Home />} />
          <Route path="/posts" element={<Posts />} />
          <Route path="/createpost" element={<CreatePost />} />
          <Route path="/updatepost/:id" element={<UpdatePost />} />
          {/* Add more routes as needed */}
        </Routes>
        
      </Router>
    </AuthProvider>
  );
}

export default App

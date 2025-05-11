import React, { useState, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setLoginError("");

    try {
      // Using axios instead of fetch
      const res = await axios.post("http://localhost:8080/api/users/login", {
        username,
        password,
      });

      if (res.status === 200) {
        const token = res.data; // Assuming the token is returned in the response body
        //localStorage.setItem('token', token);
        login(token);
        navigate("/posts"); // Redirect after login
      } else {
        throw new Error("Invalid credentials");
      }
    } catch (err) {
      setLoginError(err.message || "Login failed.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-50">
      <form
        onSubmit={handleLogin}
        className="w-full max-w-md px-6 py-8 bg-white rounded-lg shadow-md"
      >
        <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>

        {loginError && <p className="text-red-600 mb-4">{loginError}</p>}

        <input
          type="text"
          placeholder="Username"
          className="w-full p-3 mb-4 border rounded"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          className="w-full p-3 mb-4 border rounded"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button
          type="submit"
          disabled={isLoading}
          className="w-full bg-blue-600 text-white p-3 rounded hover:bg-blue-500 transition"
        >
          {isLoading ? "Logging in..." : "Login"}
        </button>
      </form>
    </div>
  );
};

export default Login;

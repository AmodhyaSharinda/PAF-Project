import React, { createContext, useState, useEffect } from "react";
import axios from "axios";

// Rename UserContext to AuthContext
export const AuthContext = createContext();

// Rename UserProvider to AuthProvider
export const AuthProvider = ({ children }) => {
  const [users, setUsers] = useState([]);
  const [currentUserToken, setCurrentUserToken] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const API_BASE = "http://localhost:8080/api/users";

  // Load all users
  const fetchUsers = async () => {
    try {
      const res = await axios.get(`${API_BASE}/getusers`);
      setUsers(res.data);
    } catch (err) {
      console.error("Failed to fetch users", err);
    }
  };

  // Create a new user
  const addUser = async (userData) => {
    try {
      const res = await axios.post(`${API_BASE}/adduser`, userData);
      return res.data;
    } catch (err) {
      console.error("User creation failed", err);
      throw err;
    }
  };

  // Login user
  const login = async (credentials) => {
    try {
      const res = await axios.post(`${API_BASE}/login`, credentials, {
      headers: {
        'Content-Type': 'application/json', // crucial to avoid 415
      },
    });
      const token = res.data;
      setCurrentUserToken(token);
      setIsAuthenticated(true);
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      return token;
    } catch (err) {
      console.error("Login failed", err);
      throw err;
    }
  };

  // Logout user
  const logout = () => {
    setCurrentUserToken(null);
    setIsAuthenticated(false);
    localStorage.removeItem("token");
    delete axios.defaults.headers.common["Authorization"];
  };

  // Auto-login if token exists
  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken) {
      setCurrentUserToken(storedToken);
      setIsAuthenticated(true);
      axios.defaults.headers.common["Authorization"] = `Bearer ${storedToken}`;
    }
  }, []);

  return (
    <AuthContext.Provider
      value={{
        users,
        fetchUsers,
        addUser,
        login,
        logout,
        isAuthenticated,
        currentUserToken,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

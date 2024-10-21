import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Dashboard from "./pages/DashBoard";
import Customers from "./pages/Customers";
import Designs from './pages/Designs';
import Blog from './pages/Blog';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/designs" element={<Designs />} />
        <Route path="/blog" element={<Blog />} />

        <Route path={"/dashboard"} element={<Dashboard />} />

        <Route path={"/customers"} element={<Customers />} />
      </Routes>
    </Router>
  );
};

export default App;

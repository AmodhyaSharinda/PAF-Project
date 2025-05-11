import React, { useContext, useState, useEffect } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import {
  FiBookOpen,
  FiUsers,
  FiShare2,
  FiTarget,
  FiCode,
  FiThumbsUp,
} from "react-icons/fi";

import BannerImg from "../../assets/images/cover.jpeg";

const Home = () => {
  const { isAuthenticated, isLoading } = useContext(AuthContext);
  const [randomQuote, setRandomQuote] = useState("");

  // Array of motivational and educational quotes
  const skillSharingQuotes = [
    "Share knowledge, empower others, and grow together.",
    "The best way to learn is to teach.",
    "Building the future with every line of code.",
    "The journey to mastering skills is a shared experience.",
    "Collaboration is the key to innovation.",
    "Great coders share their knowledge and improve the community.",
    "Together, we code better.",
    "Code is the universal language of problem-solving.",
    "Knowledge shared is knowledge multiplied.",
    "The more you share, the more you grow.",
  ];

  // Select a random quote on component mount and when the button is clicked
  useEffect(() => {
    getRandomQuote();
  }, []);

  const getRandomQuote = () => {
    const randomIndex = Math.floor(Math.random() * skillSharingQuotes.length);
    setRandomQuote(skillSharingQuotes[randomIndex]);
  };

  if (isLoading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <div className="animate-pulse text-xl font-semibold">Loading...</div>
      </div>
    );
  }

  if (isAuthenticated) {
    return <Navigate to="/dashboard" />;
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-6xl mx-auto px-4 pt-16">
        <div className="flex flex-col md:flex-row items-center gap-8 py-24">
          {/* Left Column - Hero Text and Quote */}
          <div className="md:w-1/2 w-full">
            <h1 className="text-4xl md:text-5xl font-bold text-gray-800 mb-4">
              CodeSkillsharing: Empowering Coders Worldwide
            </h1>
            <p className="text-lg text-gray-600 mb-6">
              Share your skills, learn from others, and collaborate to build
              meaningful projects with the global coding community.
            </p>

            {/* Fun Quote Section */}
            <div className="bg-gradient-to-r from-blue-50 to-indigo-50 p-7 rounded-lg border-l-4 border-blue-500 mb-8 relative">
              <p className="text-lg italic text-gray-700">{randomQuote}</p>
              <button
                onClick={getRandomQuote}
                className="absolute bottom-2 right-2 text-blue-500 text-sm hover:text-blue-700"
              >
                New quote
              </button>
            </div>
          </div>

          {/* Right Column - Images */}
          <div className="md:w-1/2 w-full">
            <img src={BannerImg} alt="CodeSkillsharing Banner" />
          </div>
        </div>

        {/* How to Use This Platform Section */}
        <div className="py-12 mb-16">
          <h2 className="text-3xl font-bold text-center mb-8 text-gray-800">
            How to Get Started on CodeSkillsharing
          </h2>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            {/* Left side: Steps to start with the platform */}
            <div className="bg-white rounded-lg p-8 shadow-md">
              <h3 className="text-xl font-semibold mb-6 text-gray-800">
                Get Started in 4 Easy Steps
              </h3>

              <ul className="space-y-6">
                <li className="flex items-start">
                  <div className="bg-blue-100 p-3 rounded-full mr-4">
                    <FiUsers className="text-blue-600 text-xl" />
                  </div>
                  <div>
                    <h4 className="font-medium text-gray-800">Create Your Profile</h4>
                    <p className="text-gray-600 mt-1">
                      Sign up with your GitHub, Google, or email to personalize your profile.
                    </p>
                  </div>
                </li>

                <li className="flex items-start">
                  <div className="bg-green-100 p-3 rounded-full mr-4">
                    <FiCode className="text-green-600 text-xl" />
                  </div>
                  <div>
                    <h4 className="font-medium text-gray-800">Share Your Code</h4>
                    <p className="text-gray-600 mt-1">
                      Share your projects, tutorials, or code snippets to help others grow.
                    </p>
                  </div>
                </li>

                <li className="flex items-start">
                  <div className="bg-purple-100 p-3 rounded-full mr-4">
                    <FiShare2 className="text-purple-600 text-xl" />
                  </div>
                  <div>
                    <h4 className="font-medium text-gray-800">Collaborate and Learn</h4>
                    <p className="text-gray-600 mt-1">
                      Collaborate with others, learn new skills, and grow together as a community.
                    </p>
                  </div>
                </li>

                <li className="flex items-start">
                  <div className="bg-orange-100 p-3 rounded-full mr-4">
                    <FiThumbsUp className="text-orange-600 text-xl" />
                  </div>
                  <div>
                    <h4 className="font-medium text-gray-800">Engage and Get Recognized</h4>
                    <p className="text-gray-600 mt-1">
                      Get feedback, engage with your community, and get recognized for your contributions.
                    </p>
                  </div>
                </li>
              </ul>
            </div>

            {/* Right side: Tips and features */}
            <div className="bg-gradient-to-br from-blue-50 to-indigo-50 rounded-lg p-8 shadow-md flex flex-col justify-between">
              <div>
                <h3 className="text-xl font-semibold mb-6 text-gray-800">
                  Pro Tips for Better Learning and Sharing
                </h3>

                <ul className="space-y-4 mb-6">
                  <li className="flex items-start">
                    <FiTarget className="text-teal-500 mt-1 mr-3 flex-shrink-0" />
                    <span className="text-gray-700">
                      Set clear goals for your projects to stay focused and motivated.
                    </span>
                  </li>
                  <li className="flex items-start">
                    <FiUsers className="text-blue-500 mt-1 mr-3 flex-shrink-0" />
                    <span className="text-gray-700">
                      Collaborate actively — the best projects come from diverse teams.
                    </span>
                  </li>
                  <li className="flex items-start">
                    <FiBookOpen className="text-purple-500 mt-1 mr-3 flex-shrink-0" />
                    <span className="text-gray-700">
                      Share what you’ve learned — teaching is the best way to reinforce your knowledge.
                    </span>
                  </li>
                </ul>
              </div>

              <div className="bg-white p-4 rounded-lg shadow-sm mt-6">
                <div className="flex items-center justify-between">
                  <p className="text-sm font-medium text-gray-800">Important</p>
                  <span className="text-xs bg-blue-100 text-blue-800 px-2 py-1 rounded-full">
                    Community Guidelines
                  </span>
                </div>
                <p className="text-sm text-gray-600 mt-2">
                  Be respectful, share knowledge, and contribute positively to the community. Follow our community guidelines for a better experience.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;

import React, { useState } from 'react';
import axios from 'axios';

const CreatePost = ({ onPostCreated }) => {
  const [caption, setCaption] = useState('');
  const [location, setLocation] = useState('');
  const [photoUrls, setPhotoUrls] = useState(['']);
  const [videoUrls, setVideoUrls] = useState(['']);

  const handleArrayInputChange = (index, value, type) => {
    const updater = type === 'photo' ? [...photoUrls] : [...videoUrls];
    updater[index] = value;
    type === 'photo' ? setPhotoUrls(updater) : setVideoUrls(updater);
  };

  const addField = (type) => {
    type === 'photo' ? setPhotoUrls([...photoUrls, '']) : setVideoUrls([...videoUrls, '']);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');

    // Send the token with the request
    try {
      const response = await axios.get('http://localhost:8080/api/users/getusers', {
        headers: {
          'Authorization': `Bearer ${token}`,
        }
      });

      // Assuming the backend returns the user object
      const user = response.data;

      const postData = {
        user: user._id, // Get the _id from the decoded user data
        caption,
        location,
        numberOfLikes: 0,
        numberOfComments: 0,
        photoUrls: photoUrls.filter((url) => url.trim() !== ''),
        videoUrls: videoUrls.filter((url) => url.trim() !== ''),
      };

      // Create the post
      await axios.post('http://localhost:8080/api/posts/addposts', postData, {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
      });

      alert('Post created!');
      if (onPostCreated) onPostCreated();  // Refresh the posts page or redirect
      setCaption('');
      setLocation('');
      setPhotoUrls(['']);
      setVideoUrls(['']);
    } catch (error) {
      console.error('Post creation failed:', error);
      alert(error.response?.data?.message || 'Failed to create post.');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label className="form-label">Caption</label>
        <input
          type="text"
          className="form-control"
          value={caption}
          onChange={(e) => setCaption(e.target.value)}
          required
        />
      </div>

      <div className="mb-3">
        <label className="form-label">Location</label>
        <input
          type="text"
          className="form-control"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
        />
      </div>

      <div className="mb-3">
        <label className="form-label">Photo URLs</label>
        {photoUrls.map((url, index) => (
          <input
            key={index}
            type="text"
            className="form-control mb-2"
            value={url}
            onChange={(e) => handleArrayInputChange(index, e.target.value, 'photo')}
            placeholder="https://example.com/photo.jpg"
          />
        ))}
        <button type="button" className="btn btn-sm btn-outline-primary" onClick={() => addField('photo')}>
          + Add Photo URL
        </button>
      </div>

      <div className="mb-3">
        <label className="form-label">Video URLs</label>
        {videoUrls.map((url, index) => (
          <input
            key={index}
            type="text"
            className="form-control mb-2"
            value={url}
            onChange={(e) => handleArrayInputChange(index, e.target.value, 'video')}
            placeholder="https://example.com/video.mp4"
          />
        ))}
        <button type="button" className="btn btn-sm btn-outline-primary" onClick={() => addField('video')}>
          + Add Video URL
        </button>
      </div>

      <button type="submit" className="btn btn-success w-100">
        Submit Post
      </button>
    </form>
  );
};

export default CreatePost;

import React, { useState, useEffect } from 'react';

const EditPost = ({ post, onPostUpdated }) => {
  const [caption, setCaption] = useState(post.caption);
  const [photoUrls, setPhotoUrls] = useState(post.photoUrls || []);
  const [videoUrls, setVideoUrls] = useState(post.videoUrls || []);

  useEffect(() => {
    setCaption(post.caption);
    setPhotoUrls(post.photoUrls);
    setVideoUrls(post.videoUrls);
  }, [post]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const updatedPost = {
      caption,
      photoUrls,
      videoUrls,
    };

    try {
      const token = localStorage.getItem('token');
      const response = await fetch(
        `http://localhost:8080/api/posts/updateposts/${post.id}`,
        {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify(updatedPost),
        }
      );
      if (response.ok) {
        onPostUpdated();
      } else {
        throw new Error('Error updating post');
      }
    } catch (error) {
      console.error('Error updating post:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label htmlFor="caption" className="form-label">
          Caption
        </label>
        <textarea
          id="caption"
          className="form-control"
          value={caption}
          onChange={(e) => setCaption(e.target.value)}
        />
      </div>

      {/* You can implement similar fields for `photoUrls` and `videoUrls` */}

      <button type="submit" className="btn btn-primary">
        Update Post
      </button>
    </form>
  );
};

export default EditPost;

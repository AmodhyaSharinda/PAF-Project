import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import CreatePost from './CreatePost';
import UpdatePost from './UpdatePost';

const Posts = () => {
  const [posts, setPosts] = useState([]);
  const [refresh, setRefresh] = useState(false);
  const [editPost, setEditPost] = useState(null);
  const [loading, setLoading] = useState(true);

  // Fetching posts from the server
  useEffect(() => {
    const token = localStorage.getItem('token');
    setLoading(true);

    fetch('http://localhost:8080/api/posts/getposts', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) throw new Error('Unauthorized or failed to fetch posts');
        return response.json();
      })
      .then((data) => {
        setPosts(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching posts:', error);
        setLoading(false);
      });
  }, [refresh]);

  // Handle the post edit
  const handleEdit = (post) => {
    setEditPost(post);
    const editPostModal = new window.bootstrap.Modal(document.getElementById('editPostModal'));
    editPostModal.show();
  };

  // Handle the post deletion
  const handleDelete = async (postId) => {
    const token = localStorage.getItem('token');
    try {
      const response = await fetch(`http://localhost:8080/api/posts/deletepost/${postId}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      const result = await response.text();

      if (!response.ok) {
        throw new Error(result || 'Failed to delete post');
      }

      alert(result);
      setRefresh((prev) => !prev); // Trigger refresh to reload posts
    } catch (error) {
      console.error('Error deleting post:', error.message);
      alert('Error deleting post: ' + error.message);
    }
  };

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h1 className="fw-bold">Community Posts</h1>
        <button
          className="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#createPostModal"
        >
          + Create Post
        </button>
      </div>

      <div className="row">
        {loading ? (
          <div className="col-12 text-center">
            <p>Loading posts...</p>
          </div>
        ) : posts.length === 0 ? (
          <div className="col-12 text-center">
            <p>No posts available</p>
          </div>
        ) : (
          posts.map((post) => (
            <div key={post._id} className="col-md-6 mb-4">
              <div className="card shadow-sm">
                <div className="card-body">
                  <div className="d-flex align-items-center mb-3">
                    <img
                      src="https://via.placeholder.com/50"
                      alt="Profile"
                      className="rounded-circle me-3"
                      style={{ width: '50px', height: '50px' }}
                    />
                    <div>
                      <h6 className="mb-0">User</h6>
                      <small className="text-muted">3 hours ago</small>
                    </div>
                  </div>

                  <p className="card-text">{post.caption}</p>

                  {post.photoUrls?.length > 0 && (
                    <div className="mb-3">
                      <h6>Photos:</h6>
                      <div className="d-flex flex-wrap">
                        {post.photoUrls.map((url, index) => (
                          <img
                            key={index}
                            src={url}
                            alt={`Post photo ${index}`}
                            className="img-thumbnail me-2"
                            style={{
                              width: '100px',
                              height: '100px',
                              objectFit: 'cover',
                            }}
                          />
                        ))}
                      </div>
                    </div>
                  )}

                  {post.videoUrls?.length > 0 && (
                    <div className="mb-3">
                      <h6>Videos:</h6>
                      {post.videoUrls.map((url, index) => (
                        <video
                          key={index}
                          className="img-fluid mb-2"
                          controls
                        >
                          <source src={url} type="video/mp4" />
                          Your browser does not support the video tag.
                        </video>
                      ))}
                    </div>
                  )}

                  <div className="d-flex justify-content-between mt-3">
                    <button className="btn btn-light">
                      ❤️ {post.numberOfLikes} Likes
                    </button>
                    <button className="btn btn-light">
                      💬 {post.numberOfComments} Comments
                    </button>
                  </div>

                  <div className="mt-2">
                    <button
                      className="btn btn-warning btn-sm"
                      onClick={() => handleEdit(post)}
                    >
                      ✏️ Edit
                    </button>
                    <button
                      className="btn btn-danger btn-sm ms-2"
                      onClick={() => handleDelete(post._id)} // Correctly pass post._id to handleDelete
                    >
                      🗑️ Delete
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))
        )}
      </div>

      {/* Create Post Modal */}
      <div className="modal fade" id="createPostModal" tabIndex="-1" aria-labelledby="createPostModalLabel" aria-hidden="true">
        <div className="modal-dialog modal-lg modal-dialog-centered">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="createPostModalLabel">Create New Post</h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              <CreatePost onPostCreated={() => setRefresh(!refresh)} />
            </div>
          </div>
        </div>
      </div>

      {/* Edit Post Modal */}
      <div className="modal fade" id="editPostModal" tabIndex="-1" aria-labelledby="editPostModalLabel" aria-hidden="true">
        <div className="modal-dialog modal-lg modal-dialog-centered">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="editPostModalLabel">Edit Post</h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              {editPost && (
                <UpdatePost
                  post={editPost}
                  onPostUpdated={() => setRefresh(!refresh)}
                />
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Posts;

import axios from 'axios';


export default {

  get(id){
    return axios.get(`/posts/details/${id}`)
  },

  getAllPosts() {
    return axios.get(`/posts`);
  },
  getPostsByForumId(forumId) {
    return axios.get(`/forums/${forumId}/posts`); 
  },

  getPostsByForumIdByNew(forumId) {
    return axios.get(`/forums/${forumId}/new`); 
  },

  getPostsByUserId(userId) {
    return axios.get(`/posts/user/${userId}`)
  },

  searchForPosts(search) {
    return axios.get(`/posts?search=${search}`);
  },

  getLikedPostsByUserId(userId) {
    return axios.get(`/posts/likes/user/${userId}`)
  },

  likePost(postId, userId) {
    return axios.post(`posts/${postId}/${userId}/true`)
  },

  dislikePost(postId, userId) {
    return axios.post(`posts/${postId}/${userId}/false`)
  },

  removeLikeOnPost(postId, userId) {
    return axios.delete(`posts/${postId}/delete_like/${userId}`)
  },

  createPost(post) {

      const url = "/posts";
      return axios.post(url, post);

  },

  getNumberOfLikes(postId) {
    const url = `/posts/details/${postId}/likes`
    return axios.get(url);
  },

  getNumberOfDislikes(postId) {
    const url = `/posts/details/${postId}/dislikes`
    return axios.get(url);
  },
  
  updatePost(post){
      const url = "/posts/" + post.id;
      return axios.put(url, post);
  },

  deletePost(id){
      const url = "/posts/details/"+id;
      return axios.delete(url)
  },

  
}



import axios from 'axios';


export default {

  getAllPosts() {
    return axios.get(`/posts`);
  },
  getPostsByForumId(forumId) {
    return axios.get(`/forums/${forumId}/posts`); 
  },

  getPostsByUserId(userId) {
    return axios.get(`/posts/user/${userId}`)
  },


  createPost(post) {

      const url = "/posts";
      return axios.post(url, post);

  },
  
  updatePost(post){
      const url = "/posts/" + post.id;
      return axios.put(url, post);
  },

  deletePost(id){
      const url = "/posts/"+id;
      return axios.delete(url)
  },

 
}



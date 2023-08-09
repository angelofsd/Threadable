import axios from 'axios';


export default {

  getAllPosts() {
    return axios.get(`/posts`);
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
  }

}

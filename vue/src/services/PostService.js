import axios from 'axios';


export default {

  get(id) {
    return axios.get(`/posts/${id}`);
  },

  createPost(post) {

      const url = "posts";
      return axios.post(url, post);

  },
  
  updatePost(post){
      const url = "posts/" + post.id;
      return axios.put(url, post);
  },

  deletePost(id){
      const url = "posts/"+id;
      return axios.delete(url)
  }

}

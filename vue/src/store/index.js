import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */

let currentToken = 0;
let currentUser = "";

if(localStorage.getItem('user') !== 'undefined') {

  currentToken = localStorage.getItem('token')
  currentUser = JSON.parse(localStorage.getItem('user'));

  if(currentToken != null) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
  }
  
}
export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    posts: [],
    post: {},
    forums: [],
    favoritedForums: [],
    likedPosts: [],
    dislikedPosts: [],
    heartedReplies: [],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      console.log(user)
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_NEW_POST(state, payload) {
      state.posts.unshift(payload);
    },
    SAVE_POST() {

    },
    SET_ACTIVE_POST() {

    },
    SET_POSTS() {
      
    },
    SET_LIKE_STATUS(state, payload) {
      state.post = payload;

    },
    ADD_NEW_FORUM(state, payload) {
      state.forums.unshift(payload);
    },
    SET_FORUMS() {
  
    },
    DELETE_POST(state, postId) {
      state.posts.splice(
        state.posts.findIndex(post => post.id === postId),
        1
      )
    },

    // FAVORITED STUFF

    SET_FAVORITED_FORUMS(state, data) {
      state.favoritedForums = data
    },
    ADD_FAVORITED_FORUM(state, payload) {
      state.favoritedForums.unshift(payload);
    },
    REMOVE_FAVORITED_FORUM(state, forumId) {
      state.favoritedForums.splice(forum => forum.id === forumId)
    },
    SET_LIKED_POSTS(state, data) {
      state.likedPosts = data
    },
    SET_DISLIKED_POSTS(state, data) {
      state.dislikedPosts = data
    },
    ADD_LIKED_POST(state, payload) {
      const foundPost = state.likedPosts.find(post => post.postId == payload.postId)
      if (!foundPost) {
        state.likedPosts.unshift(payload);
      }
    },
    REMOVE_LIKED_POST(state, postId) {
      const index = state.likedPosts.findIndex(post => post.postId == postId)
      if (index >= 0) {
        state.likedPosts.splice(index, 1)
      }
    },
    ADD_DISLIKED_POST(state, payload) {
      const foundPost = state.dislikedPosts.find(post => post.postId == payload.postId)
      if (!foundPost) {
        state.dislikedPosts.unshift(payload);
      }
    },
    REMOVE_DISLIKED_POST(state, postId) {
      const index = state.dislikedPosts.findIndex(post => post.postId == postId)
      if (index >= 0) {
        state.dislikedPosts.splice(index, 1)
      }
    }
  }
})

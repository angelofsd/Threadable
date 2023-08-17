<template>
  <div id="post-list">
    <h3>Activity</h3>
    <div class="post" v-for="post in posts" v-bind:key="post.postId">
      <div id="post-subheader">
        <div v-show="post.imageURL != null" id="img-div">
          <img :src="post.imageURL" alt="post_img">
        </div>
          <div id="body-and-likes">
            <div id="body">
              <router-link v-bind:to="{ name: 'post', params: { id: post.postId } }" >{{ post.title }}</router-link>
              <p>{{post.body}}</p>
            </div>
            <LikeAndDislike :postId="post.postId" :post="post" />
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import postService from '../services/PostService';
import LikeAndDislike from './LikeAndDislike.vue';

export default {
  components: { LikeAndDislike },
    name: 'activity',
    data() {
        return {
            posts: []
        }
    },
    created() {
        postService.getPostsByUserId(this.$route.params.id).then((response) => {
            this.posts = response.data
            console.log(response.data)
        })
    }
}
</script>


<style scoped>
#list {
  margin: 20px;
}

.post {
 padding: 10px;
}
/** page structure **/
#post-subheader{
  display:flex;
}

#body-and-likes {
  display: flex;
  justify-content: space-between;
  flex: 1;
}

#body {
  margin-left: 15px;
}
.post-details {
  padding: 20px 20px;
  margin: 0 auto;
  max-width: 75%;
}

#img-div {
  padding: 15px;
  border-right: solid 1px rgb(214, 214, 214);
}

#img-div img {
  height: 200px;
  border-radius: 5px;
}

</style>
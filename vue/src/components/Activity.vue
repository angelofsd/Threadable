<template>
    <div id="list">
        <h3>Activity</h3>
        <div id="post-list">
            <h4>Created Posts</h4>
            <div class="post" v-for="post in posts" v-bind:key="post.postId">
                <div id="post-subheader">
                  <h4>{{ post.title }}</h4>
                  <LikeAndDislike :postId="post.postId" />
                </div>
                <img v-show="post.imageURL" :src="post.imageURL" />
                <p>{{ post.body }}</p>
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
    props: {
        post: Object,
    },
    data() {
        return {
            posts: {}
        }
    },
    created() {
        postService.getPostsByUserId(this.$route.params.id).then((response) => {
            this.posts = response.data
        })
    }
}
</script>


<style>
#list {
  margin: 20px;
}

.post {
  border: solid 1px #555597;
  box-shadow: 1px 2px #555597;
  border-radius: 10px;
  margin: 20px 0px;
  padding: 5px;

}

.post:hover {
  border: solid 1px #978555;
  box-shadow: 1px 2px #978555;
}

.post p {
  font-size: 14px;
}

.post a {
  border-bottom: solid 1px #555597;
}

.post a:hover {
  border-bottom: solid 1px #978555;
}
</style>
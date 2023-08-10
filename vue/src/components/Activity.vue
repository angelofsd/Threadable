<template>
    <div id="list">
        <h3>Activity</h3>
        <div id="post-list">
            <h4>Created Posts</h4>
            <div class="post" v-for="post in posts" v-bind:key="post.id">

                <h4>{{ post.title }}</h4>
                <img v-show="post.imageURL" v-if="post.postId" v-bind:src="'' + post.postId" />
                <p>{{ post.body }}</p>
                <div class="button-container">
                    <button class="mark-liked" v-on:click.prevent="setLike(true)" v-if="!post.liked">Mark Like</button>
                    <button class="mark-unliked" v-on:click.prevent="setLike(false)" v-if="post.liked">Mark Unlike</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import postService from '../services/PostService';

export default {
  components: { },
    name: 'post-card',
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
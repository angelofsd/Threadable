<template>
  <div class="card">

    <h2 class="post-title"><router-link :to="{name: 'postDetails', params: {id: post.postId}}">{{ post.title }}</router-link></h2>
    <img v-if="post.postId" v-bind:src="'' + post.postId" />
    <h4 class="post-body"><router-link :to="{name: 'postDetails', params: {id: post.postId}}">{{ post.body }}</router-link></h4>
    <div class="button-container" >
        <button class="mark-liked" v-on:click.prevent="setLike(true)" v-if="!post.liked">Mark Like</button>
        <button class="mark-unliked" v-on:click.prevent="setLike(false)" v-if="post.liked">Mark Unlike</button>
    </div>
    
  </div>
</template>

<script>
export default {
    name: 'post-card',
    props: {
        post: Object,
    },
    methods: {
        setLike(value) {
            this.$store.commit('SET_LIKE_STATUS', {post: this.post, value: value});
        }
    }
}
</script>

<style>
.card {
    border: 2px solid black;
    border-radius: 10px;
    width: 250px;
    height: 550px;
    margin: 20px;
}

.card.read {
    background-color: lightgray;
}

.card .post-title {
    font-size: 1.5rem;
}

.card .post-body {
    font-size: 1rem;
}
</style>
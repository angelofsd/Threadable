<template>
  <form class="new-post-form" v-on:submit.prevent="savePost">
    <input class="title-input" type="text" placeholder="Title" v-model="post.title" />
    <input class="body-input" type="textarea" placeholder="Body" v-model="post.body" />
    <input class="image-input" type="text" placeholder="Image" v-model="post.image" />
    <button>Save</button>
  </form>
</template>

<script>
export default {
    name: "new-post-form",
    data() {
        return {
            post: {
                title: '',
                body: '',
                image: '',
                liked: null,
            }
        }
    },
    methods: {
        savePost() {
            this.$store.commit('ADD_NEW_POST', this.post);
            this.post = {
                title: '',
                body: '',
                image: '',
                liked: null,
            };
            this.$router.push({name: 'home'});
        }

    }

}
</script>

<style>
.new-post-form {
    margin: 20px;
}

.new-post-form input, .new-post-form button {
    margin: 10px;
    font-size: 1rem;
}
</style>
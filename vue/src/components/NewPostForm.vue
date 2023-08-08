<template>
  <form class="new-post-form" v-on:submit.prevent="savePost">
    <input class="title-input" type="text" placeholder="Title" v-model="post.title" />
    <input class="body-input" type="text" placeholder="Body" v-model="post.body" />
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
                liked: null,
                image: ''
            }
        }
    },
    methods: {
        savePost() {
            this.$store.commit('SAVE_POST', this.post);
            this.post = {
                title: '',
                body: '',
                liked: null,
                image: ''
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
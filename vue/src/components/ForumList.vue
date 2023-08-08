<template>
  <div class="forum-list">
      <div class="testDiv" v-for="forum in forums" v-bind:key="forum.id">
        <h2>  {{forum.name}} </h2>
      </div>

  </div>
</template>

<script>
import ForumService from '../services/ForumService.js';

export default {
    name:'forum-list',
    props: {
        forumId: {
            name: "",
            createdBy: "",
            description: "",
            dateCreated: "",
        }
    },
    data() {
        return {
            forums: [],
        }
    },
    methods: {
        getForums() {
            ForumService.list().then((response) => {
                if (response.status === 200) {
                    this.forums=response.data;
                }
            })
            }
        },
    created() {
        this.getForums();
    }
}
</script>

<style>

</style>
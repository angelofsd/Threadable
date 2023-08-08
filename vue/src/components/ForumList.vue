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
            .catch( (error) => {
                if (error.response) {
                alert("Something went wrong: " + error.response.statusText);
                } else if(error.request){
                            //We could not reach the server
                            alert("We could not reach the server");
                } else {
                            alert("Something went horribly wrong");
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
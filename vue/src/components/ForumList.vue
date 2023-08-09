<template>
  <div class="forumList">
      <div class="testDiv" v-for="forum in forums" v-bind:key="forum.id">
        <h2 class="forum-name">  {{forum.name}} </h2>
        <h5 class="forum-description"> {{forum.description}}</h5>
        <h5 class="created-by">Created By: {{forum.createdBy}}</h5>
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

<style scoped>
.testDiv {
    border: solid 2px #889DAE;
    padding-left: 10px;
    padding-right: 10px;
    margin-bottom:10px;
    border-radius: 8px;
    
}
.forum-description {
    display: inline-block;
    width:50%;
    flex-direction: row-reverse;
}

.created-by {
    display: inline-block;
    width:50%;
    text-align:right;
    font-weight: normal;
}

</style>
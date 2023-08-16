<template>
  <form v-on:submit.prevent>
      <div>
        <label for="forumName">Name of Forum: </label>
        <input required type="text" name="postTitle" v-model="forum.name"/>
      </div>
      <div>
        <label for="forumDescription">Description: </label>
        <textarea required name="forumDescription" rows = "10" v-model="forum.description"/>
      </div>
      <div class="saveForum">
          <button type="submit" id="save" v-on:click="saveForum()">Save Forum</button>
      </div>
  </form>
</template>

<script>
import ForumService from '../services/ForumService.js'

export default {
    name: "create-forum",
    props:["forumId"],
    data() {
        return {
            forum: {
                id: this.forumId,
                name: '',
                description: '',
                createdBy: this.$store.state.user.id,
                dateCreated: ''
            }
        };
    },
    computed: {
      userId() {
          return this.$store.state.user.id;
      }
  },
    methods: {
        saveForum() {
            ForumService.create(this.forum).then((response) => {
                this.id = response.data.id;
                this.name = response.data.name;
                this.description = response.data.name;
                this.createdBy = response.data.createdBy;
                this.dateCreated = response.data.dateCreated;
                if (response.status === 200 || response.status === 201) {
                    console.log("Successfully created a forum!");
                }
                window.location.reload();
            })
            .catch((error) => {
                if(error.response) {
                    
                    alert("Something went wrong: " + error.response.statusText);
                } else if(error.request){
                    
                    alert("We could not reach the server");
                } else {
                    alert("Something went horribly wrong");
                }
            })
        }
    }
}
</script>

<style scoped>
div {
    display: flex;
    flex-direction: column;
}

input[name="postTitle"] {
    width: 25%
}
textarea {
    padding: 10px;

    font-family: 'Montserrat', sans-serif;
     font-size: 14px;
     font-weight:500;
     background-color: #FFFFFF;
     color: #000000;
     border-style: solid;
     border-radius: 14px;
     box-shadow: 3px 3px 5px rgba(66,66,66,.75);
     margin-left: 10px;

}

button {
    background-color: #5390b3;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
label {
    padding-top: 10px;
    padding-bottom: 10px;
    font-family: 'Montserrat', sans-serif;
     font-size: 14px;
     font-weight: bold;

}


  button:hover {
    background-color: #225979;
  }

  #save {
      width: 20%;
      margin-top: 20px;
  }

  
</style>
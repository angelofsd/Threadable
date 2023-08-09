<template>
  <div class="active-forums"> 
  <ForumList></ForumList>
  </div>
</template>


<script>
import ForumList from '../components/ForumList.vue'

export default {
  name: "active-forums",
  components: {
    ForumList
  },
  
  data() {
    return {
      forum: {},
      invalidCredentials: false
    };
  },
};
</script>
<template>
  <div>
    <div class="hot-posts">
        <post-list/>
    </div>
  </div>
</template>

<script>
import PostList from '../components/PostList.vue';
export default {
  name: "user-profile",
  components: {PostList},
  data() {
    return {
      user: {},
      invalidCredentials: false
    };
  },
};
</script>
<style>
#post-list {
  margin: 20px;
}

.post {
  background-color: rgb(200, 216, 246);
  color: black;
  border: solid 1px #e9e9f3;
  box-shadow: 1px 2px #e5e5f6;
  border-radius: 10px;
  margin: 20px 0px;
  padding: 5px;

}

.post:hover {
  border: solid 1px #07a3eb;
  box-shadow: 1px 2px #07a3eb;
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
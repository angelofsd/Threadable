<template>
  <div id="login">
    <form @submit.prevent="login">
      <h1 >Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <input type="text" id="username" v-model="user.username" placeholder="Username" required autofocus />
      </div>
      <div class="form-input-group">
        <input type="password" id="password" v-model="user.password" placeholder="Password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#login {
  text-align: center;
  min-width: 20%;
  background-color: #d9e1e7;
  margin: 10%;
  border-radius: 20px;
  padding: 2px;
  box-shadow: 2px 2px 3px #555597;
  color: #225979;
}

h1{
  font-size: 24px;
}

input {
  padding-right: 25%;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  font-size: 12px;
  border: solid 2px #889DAE;
  border-radius: 8px;
  margin: 10px;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>
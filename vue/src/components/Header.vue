<template>
  <div id="header">
    <div id="banner">
        <router-link id="logo" v-bind:to="{ name: 'home' }"><h1>Reddit (Lite)</h1></router-link>
        <div id="nav">
            <div id="login-text">
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
            <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
            <p v-show="$store.state.token != ''">You are logged in as {{$store.state.user.username}}</p>
            </div>
        </div>
    </div>
      <form id="search">
          <input type="text" placeholder="Search..."/>
      </form>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    input {
        padding-right: 300px;
        padding-top: 10px;
        padding-bottom: 10px;
        padding-left: 10px;
        font-size: 18px;
        border: solid 2px #889DAE;
        border-radius: 8px;
        margin: 10px;
    }

    #header {
        border-bottom: solid 1px #889DAE;
        padding-bottom: 25px;
    }

    #banner {
        display: flex;
    }

    #logo {
        background-color: cadetblue;
    }

    #nav {
        text-align: right;
        background-color: blue;
        /* Why doesn't this WORK */
        justify-content: flex-end;
    }

    #header h1 {
        font-family: 'Unica One', 'Montserrat', sans-serif;
        cursor: pointer;
    }

    #header h1:hover {
        color:rgb(85, 85, 151);
        transition-duration: 250ms;
    }

    #header h1:not( :hover ) {
        transition-duration: 250ms;
    }

    a {
        text-decoration: none;
        color: inherit;
    }

    #search {
        text-align: center;
    }
    
</style>
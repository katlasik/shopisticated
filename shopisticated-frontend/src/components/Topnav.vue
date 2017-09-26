<template>
    <b-navbar toggleable="md" type="dark" variant="info">
        <b-nav-toggle target="nav_collapse"></b-nav-toggle>
        <b-navbar-brand href="/">Shopisticated</b-navbar-brand>
        <b-collapse is-nav id="nav_collapse">
            <b-nav is-nav-bar>
                <li class="nav-item">
                    <router-link class="nav-link" to="/">Buy</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/summary">Summary</router-link>
                </li>
            </b-nav>
        </b-collapse>
    </b-navbar>
</template>

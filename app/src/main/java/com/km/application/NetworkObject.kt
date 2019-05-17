package com.km.application

object NetworkObject {
    data class UserInfo (
        val id: Long,
        val avatar_url: String,
        val html_url: String,
        val login: String,
        val company: String
    )

    data class Repo (
        val id: Long,
        val name: String,
        val html_url: String,
        val description: String,
        val stargazers_count: Long,
        val watchers_count: Long,
        val forks_count: Long
    )
}
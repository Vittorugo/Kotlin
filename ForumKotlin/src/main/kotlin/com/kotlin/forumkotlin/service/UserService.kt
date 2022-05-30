package com.kotlin.forumkotlin.service

import com.kotlin.forumkotlin.dto.AtualizacaoUsuarioForm
import com.kotlin.forumkotlin.dto.UsuarioForm
import com.kotlin.forumkotlin.dto.UsuarioView
import com.kotlin.forumkotlin.exception.NotFoundException
import com.kotlin.forumkotlin.mapper.FormToUsuairoMapper
import com.kotlin.forumkotlin.mapper.UsuarioToViewMapper
import com.kotlin.forumkotlin.model.Usuario
import com.kotlin.forumkotlin.repository.UsuarioRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UsuarioRepository,
    private val formToUsuarioMapper: FormToUsuairoMapper,
    private val usuarioToViewMapper: UsuarioToViewMapper
) : UserDetailsService {

    fun listar(pageable: Pageable): Page<UsuarioView> = repository.findAll(pageable).map { it -> usuarioToViewMapper.map(it) }

    fun cadastrar(usuarioForm: UsuarioForm): UsuarioView {
        val usuario: Usuario = formToUsuarioMapper.map(usuarioForm)
        return usuarioToViewMapper.map(repository.save(usuario))
    }

    fun atualizar(usuario: AtualizacaoUsuarioForm): UsuarioView {
        val usuarioAtualizado: Usuario = repository.findById(usuario.id).orElseThrow { NotFoundException("Usuário com id: ${usuario.id} não encontrado!")}
        if(usuario.nome.isNotEmpty() && usuarioAtualizado.nome.uppercase() != usuario.nome.uppercase())
            usuarioAtualizado.nome = usuario.nome
        if(usuario.email.isNotEmpty() && usuarioAtualizado.email.uppercase() != usuario.email.uppercase())
            usuarioAtualizado.email = usuario.email
        if(usuario.password.isNotEmpty() && usuarioAtualizado.password.uppercase() != usuario.password.uppercase())
            usuarioAtualizado.password = usuario.password
        return usuarioToViewMapper.map(repository.save(usuarioAtualizado))
    }

    fun deletar(id: Long): String {
        repository.deleteById(id)
        return "Usuario deleteado com sucesso!"
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw NotFoundException("Usuário não encontrado!")
        return UserDetail(usuario)
    }
}

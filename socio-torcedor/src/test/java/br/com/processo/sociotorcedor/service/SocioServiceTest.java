package br.com.processo.sociotorcedor.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.processo.sociotorcedor.entity.Socio;
import br.com.processo.sociotorcedor.repository.SocioRepository;

@RunWith(MockitoJUnitRunner.class)
public class SocioServiceTest {
	
	@Mock
	private SocioRepository socioRepository;
	
	@InjectMocks
	private SocioService socioService = new SocioServiceImpl();
	
	private Socio criarSocioOk(){
		Socio socio = new Socio();
		socio.setId(1L);
		socio.setNome("Vagner");
		socio.setEmail("develoer71@gmail.com");
		socio.setIdClube(1l);
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String format = "1990-10-12";
		socio.setDtNascimento(LocalDate.parse(format));
		return socio;
	}
	   

	

	@Test
	public void testFindById() {
		
		Socio socio = criarSocioOk();
		when(socioRepository.findOne(eq(socio.getId()))).thenReturn(socio);
		Socio retorno = socioService.findById(1L);
		Assert.assertEquals(retorno.getId(), socio.getId());
		
	}

	@Test
	public void testFindByNome() {
		Socio socio = criarSocioOk();
		when(socioRepository.findOne(eq(socio.getId()))).thenReturn(socio);
		Socio retorno = socioService.findById(1L);
		Assert.assertEquals(retorno.getNome(), socio.getNome());
	}

	@Test
	public void testFindByEmail() {
		Socio socio = criarSocioOk();
		when(socioRepository.findOne(eq(socio.getId()))).thenReturn(socio);
		Socio retorno = socioService.findById(1L);
		Assert.assertEquals(retorno.getEmail(), socio.getEmail());
				
	}



}

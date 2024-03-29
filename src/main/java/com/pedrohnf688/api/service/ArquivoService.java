package com.pedrohnf688.api.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pedrohnf688.api.helper.FileStorageException;
import com.pedrohnf688.api.helper.MyFileNotFoundException;
import com.pedrohnf688.api.modelo.Arquivo;
import com.pedrohnf688.api.repository.ArquivoRepository;

@Service
public class ArquivoService {

	@Autowired
	private ArquivoRepository arquivoRepository;

	public Arquivo storeFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			if (fileName.contains("..")) {
				throw new FileStorageException(
						"Desculpa! Nome do arquivo contém sequência de caminho inválida " + fileName);
			}

			Arquivo arquivo = new Arquivo(fileName, file.getContentType(), file.getBytes());

			return arquivoRepository.save(arquivo);
		} catch (IOException ex) {
			throw new FileStorageException(
					"Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
		}
	}

	public Arquivo getFile(String fileId) {
		return arquivoRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("Arquivo não encontrado com o id " + fileId));
	}
}

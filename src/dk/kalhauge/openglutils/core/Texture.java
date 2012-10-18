package dk.kalhauge.openglutils.core;

import static android.opengl.GLES20.*;
import dk.kalhauge.openglutils.exceptions.TextureSetupExecption;
import dk.kalhauge.openglutils.interfaces.ShaderAttachable;
import android.graphics.Bitmap;
import android.opengl.GLUtils;
import android.util.Log;

public class Texture implements ShaderAttachable {
	private static int tex_counter = 0;

	public static void delete(Texture ... textures) {
		int[] texture_ids = new int[textures.length];
		for(int i = 0; i < textures.length; ++i) {
			texture_ids[i] = textures[i].texture_id;
		}
		glDeleteTextures(textures.length, texture_ids,0);
	}	    
	
	public static Texture[] create(int n) {
		int[] texture_ids = new int[n];
		glGenTextures(n, texture_ids,0);
		Texture[] textures = new Texture[n];
		for(int i = 0; i < n; ++i) {
			textures[i] = new Texture();
			textures[i].texture_id = texture_ids[i];
		}
		return textures;
	}
	
	private int texture_id;
	private int tex_number = tex_counter++;
	private int target;


	
	public void create() {
		int[] texture = new int[1];
		glGenTextures(1, texture,0);
		texture_id = texture[0];
	}
	
	public void delete(){
		Texture.delete(this);
	}
	
	public void setup(int target){
		setup(target, GL_NEAREST, GL_LINEAR, GL_REPEAT, GL_REPEAT);
	}
	
	public void setup(int target,int min_filter,int mag_filter,int wrap_s, int wrap_t) {
		this.target = target;
		bind();
		glTexParameteri(target, GL_TEXTURE_MAG_FILTER, mag_filter);
		glTexParameteri(target, GL_TEXTURE_MIN_FILTER, min_filter);
		glTexParameteri(target, GL_TEXTURE_WRAP_S, wrap_s);
		glTexParameteri(target, GL_TEXTURE_WRAP_T, wrap_t);
		unbind();
		
		
	}
	
	public void load2DImage(Bitmap bitmap) {
		if(target != GL_TEXTURE_2D) throw new TextureSetupExecption();
		bind();
		glPixelStorei(GL_UNPACK_ALIGNMENT, GL_TRUE);
		GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
		unbind();
	}

	public void bind() {
		glBindTexture(target,texture_id);
	}

	public void unbind() {
		glBindTexture(target,0);
	}


	public void attach(int position) {
		glUniform1i(position, tex_number);
		glActiveTexture(GL_TEXTURE0 + tex_number);
		bind();
		
	}
	
}

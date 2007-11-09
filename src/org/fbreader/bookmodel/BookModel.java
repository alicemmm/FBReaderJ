package org.fbreader.bookmodel;

import java.util.HashMap;
import java.util.Map;

import org.zlibrary.text.model.ZLTextModel;
import org.zlibrary.text.model.ZLTextParagraph;
import org.zlibrary.text.model.ZLTextPlainModel;
import org.zlibrary.text.model.impl.ZLModelFactory;

public class BookModel {
	private ZLTextPlainModel myBookModel = (new ZLModelFactory()).createPlainModel();
	private ContentsModel myContentsModel = new ContentsModel();
	private Map<String, ZLTextPlainModel> myFootnotes = 
		new HashMap<String, ZLTextPlainModel>();
	private Map<String, Label> myInternalHyperlinks =
		new HashMap<String, Label>();
	
	private class Label {
		final int paragraphNumber;
		final ZLTextModel model;
		
		Label(ZLTextModel model, int paragraph) {
			paragraphNumber = paragraph;
			this.model = model;
		}
	}
	
	public ZLTextPlainModel getBookModel() {
		return myBookModel;
	}
	
	public ContentsModel getContentsModel() {
		return myContentsModel;
	}
	
	public ZLTextPlainModel getFootnoteModel(String id) {
		if (!myFootnotes.containsKey(id)) {
			myFootnotes.put(id, (new ZLModelFactory()).createPlainModel()); 
		}
		return myFootnotes.get(id); 
	}
	
	public Map<String, ZLTextPlainModel> getFootnotes() {
		return myFootnotes;
	}
	
	public void addHyperlinkLabel(String label, ZLTextModel model, 
			int paragraphNumber) {
		myInternalHyperlinks.put(label, new Label(model, paragraphNumber));
	}
	
	//tmp	
	public ZLTextParagraph getParagraphByLink(String link) {
		if (myInternalHyperlinks.containsKey(link)) {
			return myInternalHyperlinks.get(link).model.getParagraph(myInternalHyperlinks.get(link).paragraphNumber);
		}
		return null;
	}
}
